//package com.example.ConversationAIBackend.service;
//
//import com.example.ConversationAIBackend.entity.*;
//import com.example.ConversationAIBackend.repository.*;
//import com.opencsv.CSVReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.boot.CommandLineRunner;
//
//import java.io.FileReader;
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class DataLoaderService implements CommandLineRunner {
//
//    @Autowired private DistributionCenterRepository distributionCenterRepo;
//    @Autowired private ProductRepository productRepo;
//    @Autowired private InventoryItemRepository inventoryItemRepo;
//    @Autowired private UserRepository userRepo;
//    @Autowired private OrderRepository orderRepo;
//    @Autowired private OrderItemRepository orderItemRepo;
//
//    private static final String DATA_PATH =
//            "C:\\Users\\HP\\OneDrive\\Documents\\Projects\\ConversationalAI\\backend\\src\\main\\resources\\data\\";
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadDistributionCenters();
//        loadProducts();
//        loadUsers();
//        loadOrders();
//        loadInventoryItems();
//        loadOrderItems();
//        System.out.println("✅ Data loaded successfully!");
//    }
//
//    private void loadDistributionCenters() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "distribution_centers.csv"))) {
//            reader.readNext(); // skip header
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                DistributionCenter dc = new DistributionCenter();
//                dc.setId(Long.parseLong(line[0]));
//                dc.setName(line[1]);
//                dc.setLatitude(Double.parseDouble(line[2]));
//                dc.setLongitude(Double.parseDouble(line[3]));
//                distributionCenterRepo.save(dc);
//            }
//        }
//    }
//
//    private void loadProducts() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "products.csv"))) {
//            reader.readNext();
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                Product p = new Product();
//                p.setId(Long.parseLong(line[0]));
//                p.setCost(Double.valueOf(line[1]));
//                p.setCategory(line[2]);
//                p.setName(line[3]);
//                p.setBrand(line[4]);
//                p.setRetailPrice(Double.valueOf(line[5]));
//                p.setDepartment(line[6]);
//                p.setSku(line[7]);
//
//                DistributionCenter dc = distributionCenterRepo.findById(Long.parseLong(line[8])).orElse(null);
//                p.setDistributionCenter(dc);
//
//                productRepo.save(p);
//            }
//        }
//    }
//
//    private void loadUsers() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "users.csv"))) {
//            reader.readNext();
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                User u = new User();
//                u.setId(Long.parseLong(line[0]));
//                u.setFirstName(line[1]);
//                u.setLastName(line[2]);
//                u.setEmail(line[3]);
//                u.setAge(line[4].isEmpty() ? null : Integer.parseInt(line[4]));
//                u.setGender(line[5]);
//                u.setState(line[6]);
//                u.setStreetAddress(line[7]);
//                u.setPostalCode(line[8]);
//                u.setCity(line[9]);
//                u.setCountry(line[10]);
//                u.setLatitude(line[11].isEmpty() ? null : Double.parseDouble(line[11]));
//                u.setLongitude(line[12].isEmpty() ? null : Double.parseDouble(line[12]));
//                u.setTrafficSource(line[13]);
//                u.setCreatedAt(parseDate(line[14]));
//                userRepo.save(u);
//            }
//        }
//    }
//
//    private void loadOrders() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "orders.csv"))) {
//            reader.readNext();
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                Order o = new Order();
//                o.setOrderId(Long.parseLong(line[0]));
//
//                User u = userRepo.findById(Long.parseLong(line[1])).orElse(null);
//                o.setUser(u);
//
//                o.setStatus(line[2]);
//                o.setGender(line[3]);
//                o.setCreatedAt(parseDate(line[4]));
//                o.setReturnedAt(parseDate(line[5]));
//                o.setShippedAt(parseDate(line[6]));
//                o.setDeliveredAt(parseDate(line[7]));
//                o.setNumOfItem(line[8].isEmpty() ? null : Integer.parseInt(line[8]));
//                orderRepo.save(o);
//            }
//        }
//    }
//
//    private void loadInventoryItems() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "inventory_items.csv"))) {
//            reader.readNext();
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                InventoryItem ii = new InventoryItem();
//                ii.setId(Long.parseLong(line[0]));
//
//                Product p = productRepo.findById(Long.parseLong(line[1])).orElse(null);
//                ii.setProduct(p);
//
//                ii.setCreatedAt(parseDate(line[2]));
//                ii.setSoldAt(parseDate(line[3]));
//                ii.setCost(Double.valueOf(line[4]));
//                ii.setProductCategory(line[5]);
//                ii.setProductName(line[6]);
//                ii.setProductBrand(line[7]);
//                ii.setProductRetailPrice(Double.valueOf(line[8]));
//                ii.setProductDepartment(line[9]);
//                ii.setProductSku(line[10]);
//
//                DistributionCenter dc = distributionCenterRepo.findById(Long.parseLong(line[11])).orElse(null);
//                ii.setProductDistributionCenter(dc);
//
//                inventoryItemRepo.save(ii);
//            }
//        }
//    }
//
//    private void loadOrderItems() throws Exception {
//        try (CSVReader reader = new CSVReader(new FileReader(DATA_PATH + "order_items.csv"))) {
//            reader.readNext();
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                OrderItem oi = new OrderItem();
//                oi.setId(Long.parseLong(line[0]));
//
//                Order o = orderRepo.findById(Long.parseLong(line[1])).orElse(null);
//                oi.setOrder(o);
//
//                User u = userRepo.findById(Long.parseLong(line[2])).orElse(null);
//                oi.setUser(u);
//
//                Product p = productRepo.findById(Long.parseLong(line[3])).orElse(null);
//                oi.setProduct(p);
//
//                InventoryItem ii = inventoryItemRepo.findById(Long.parseLong(line[4])).orElse(null);
//                oi.setInventoryItem(ii);
//
//                oi.setStatus(line[5]);
//                oi.setCreatedAt(parseDate(line[6]));
//                oi.setShippedAt(parseDate(line[7]));
//                oi.setDeliveredAt(parseDate(line[8]));
//                oi.setReturnedAt(parseDate(line[9]));
//
//                orderItemRepo.save(oi);
//            }
//        }
//    }
//
//    private Instant parseDate(String dateStr) {
//        if (dateStr == null || dateStr.isEmpty()) return null;
//
//        try {
//            // Handle ISO-like with offset (e.g., 2022-07-19 13:51:00+00:00)
//            if (dateStr.contains("+")) {
//                String isoDate = dateStr.replace(" ", "T");
//                return Instant.parse(isoDate);
//            }
//
//            // Handle plain timestamp (yyyy-MM-dd HH:mm:ss)
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//                    .withZone(ZoneOffset.UTC);
//            return Instant.from(formatter.parse(dateStr));
//        } catch (Exception e) {
//            throw new RuntimeException("❌ Failed to parse date: " + dateStr, e);
//        }
//    }
//}
