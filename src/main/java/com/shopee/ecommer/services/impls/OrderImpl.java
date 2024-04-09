package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.entities.*;
import com.shopee.ecommer.mappers.OrderDetailMapper;
import com.shopee.ecommer.models.requests.OrderDetailRequestDto;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import com.shopee.ecommer.mybatis.OrderBatisService;
import com.shopee.ecommer.repositories.AccountRepository;
import com.shopee.ecommer.repositories.OrderDetailRepository;
import com.shopee.ecommer.repositories.OrderRepository;
import com.shopee.ecommer.repositories.ProductRepository;
import com.shopee.ecommer.services.OrderService;
import com.shopee.ecommer.utils.FunctionUtils;
import com.shopee.ecommer.utils.SecurityUtil;
import com.shopee.ecommer.validators.OrderValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.shopee.ecommer.entities.OrderEcommer.Fields.*;
import static com.shopee.ecommer.utils.FunctionUtils.handlerListSort;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderImpl extends AdapterImpl implements OrderService {

    protected final OrderRepository orderRepository;

    protected final OrderDetailRepository orderDetailRepository;

    protected final OrderValidator orderValidator;

    protected final OrderBatisService orderBatisService;

    protected final ProductRepository productRepository;

    protected final AccountRepository accountRepository;

    public static List<String> getAllListDefault() {
        return new ArrayList<>(Arrays.asList(OrderEcommer.Fields.id, orderDate, shipCity, shippedDate, shipRegion));
    }

    @Transactional
    @Override
    public OrderResponseDto createOrder(OrderRequestDto data) {
        orderValidator.validateCreateOrder(data);
        Account account = accountRepository.findByUsername(SecurityUtil.getCurrentUserName());
        UUID orderId = UUID.randomUUID();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        OrderEcommer orderEcommer = OrderEcommer.builder()
                .id(orderId)
                .orderDate(new Date())
                .shipCity(data.getShipCity())
                .shippedDate(FunctionUtils.parseStringToDate().apply(data.getShippedDate()))
                .shipRegion(data.getShipRegion())
                .account(account)
                .build();
        for (OrderDetailRequestDto orderDetailRequestDto : data.getOrderDetailRequestDtoList()) {
            Product product = productRepository.findById(UUID.fromString(orderDetailRequestDto.getProductId())).get();
            product.setQuantity(product.getQuantity() - orderDetailRequestDto.getQuantity());
            OrderDetail orderDetail = OrderDetail.builder()
                    .id(UUID.randomUUID())
                    .orderId(orderId)
                    .productId(product.getId())
                    .discount(product.getDiscount())
                    .quantity(orderDetailRequestDto.getQuantity())
                    .totalAmount(orderDetailRequestDto.getQuantity() * product.getPrice())
                    .build();
            productList.add(product);
            orderDetailList.add(orderDetail);
        }
        orderRepository.save(orderEcommer);
        productRepository.saveAll(productList);
        orderDetailRepository.saveAll(orderDetailList);
        orderEcommer.setOrderDetailList(orderDetailList);
        return orderToOrderResponseDto(orderEcommer);
    }

    private OrderResponseDto orderToOrderResponseDto(OrderEcommer orderEcommer){
        OrderResponseDto orderResponseDto = OrderDetailMapper.MAPPER.orderEcommerToOrderResponseDto(orderEcommer);
        orderResponseDto.username = orderEcommer.getAccount().getUsername();
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto updateOrder(OrderRequestDto data) {
        //TODO Implement later
        return null;
    }

    @Override
    public CommonPageInfo<OrderResponseDto> getList(Map<String, String> listFields, OrderRequestDto orderRequestDto) {
        orderRequestDto.setListStringSorted(handlerListSort(orderRequestDto.getListSorted()));
        orderRequestDto.setListFields(convertListFieldRequest(listFields, getAllListDefault()));
        orderRequestDto.setTotalRecord(getCommonTotalPage().apply(orderBatisService.getList(orderRequestDto, true)));
        return CommonPageInfo.<OrderResponseDto>builder()
                .page(orderRequestDto.getPage())
                .size(orderRequestDto.getSize())
                .total(orderRequestDto.getTotalRecord())
                .data(handlerList(orderRequestDto))
                .build();
    }

    @Override
    public CommonPageInfo<OrderResponseDto> getDetail(Map<String, String> listFields, OrderRequestDto data) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(OrderRequestDto orderRequestDto) {
        orderValidator.validateListFieldRequest(orderRequestDto);
        return orderBatisService.getList(orderRequestDto, false);
    }

    @Override
    public List<String> getListField(OrderRequestDto data) {
        return checkList().apply(data.getListFields(), OrderImpl.getAllListDefault());
    }


    private List<OrderResponseDto> handlerList(OrderRequestDto orderRequestDto) {
        return checkPageSize().test(CommonPageInfo.builder()
                .page(orderRequestDto.getPage())
                .size(orderRequestDto.getSize())
                .total(orderRequestDto.getTotalRecord())
                .build()) ? orderBatisService.getList(orderRequestDto, false)
                .stream().map(OrderDetailMapper.MAPPER::mapToOrderResponseDto).toList() : new ArrayList<>();
    }
}
