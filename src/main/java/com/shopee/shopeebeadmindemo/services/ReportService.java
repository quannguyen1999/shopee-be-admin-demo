package com.shopee.shopeebeadmindemo.services;

import java.util.List;

public interface ReportService {
    <T> byte[] printReport(List<T> data);
}
