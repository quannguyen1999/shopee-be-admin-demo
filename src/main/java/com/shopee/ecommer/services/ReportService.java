package com.shopee.ecommer.services;

import java.util.HashMap;
import java.util.List;

public interface ReportService {
    byte[] printReport(List<HashMap<String, Object>> listData, List<String> listFields);
}
