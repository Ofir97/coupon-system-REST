package com.ofir.coupons.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ofir.coupons.beans.Company;
import com.ofir.coupons.beans.Coupon;
import com.ofir.coupons.beans.Customer;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.AdminService;
import com.ofir.coupons.services.CompanyService;
import com.ofir.coupons.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Test {

	private final LoginManager loginManager;

	public void testAll() {
		try {
			testAdmin();
			testCompany();
			testCustomer();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testAdmin() throws Exception {
//    	AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

//    	adminService.addCompany(Company.builder()
//                .name("my united4")
//                .email("unitedgmail.com")
//                .password("b")
//                .build());

//    	adminService.updateCompany(Company.builder()
//                .id(530)
//                .name("British Airways 11")
//                .email("ba@gmail.com")
//                .password("ua")
//                .build());

//    	adminService.deleteCompany(60);

//    	adminService.getAllCompanies().forEach(company -> System.out.println(company));

//    	System.out.println(adminService.getOneCompany(54));

//      adminService.addCustomer(Customer.builder()
//                .firstName("Avi")
//                .lastName("Moyal")
//                .password("avi123")
//                .email("avi@gmail.com")
//                .build());

//      adminService.updateCustomer(Customer.builder()
//                .id(28)
//                .firstName("Steve")
//                .lastName("Goren")
//                .password("stevenson")
//                .email("gorenush@walla.com")
//                .build());

//    	adminService.deleteCustomer(33);

//    	adminService.getAllCustomers().forEach(customer -> System.out.println(customer));

//    	System.out.println(adminService.getOneCustomer(23));

	}

	public void testCompany() throws Exception {
//		CompanyService companyService = (CompanyService) loginManager.login("elal@gmail.com", "999999",
//				ClientType.COMPANY);

//       companyService.addCoupon(Coupon.builder()
//                 .category(Category.VACATION)
//                 .title("Flight to Venice")
//                 .description("20% discount on all flights to Venice")
//                 .startDate(new Date(System.currentTimeMillis()))
//                 .endDate(Date.valueOf("2022-12-5"))
//                 .amount(5)
//                 .price(20)
//                 .image("https://d2bgjx2gb489de.cloudfront.net/gbb-blogs/wp-content/uploads/2017/05/16213722/Berlin_city_viewXL.jpg")
//                 .build());

//		companyService.updateCoupon(Coupon.builder()
//				.id(85)
//				.category(Category.VACATION)
//				.title("flight to Venice")
//				.description("15% discount on all flights to Venice")
//				.startDate(new Date(System.currentTimeMillis()))
//				.endDate(Date.valueOf("2022-12-5"))
//				.amount(5)
//				.price(1000)
//				.image("https://d2bgjx2gb489de.cloudfront.net/gbb-blogs/wp-content/uploads/2017/05/16213722/Berlin_city_viewXL.jpg")
//				.build());

//        companyService.deleteCoupon(75);

//        companyService.getCompanyCoupons().forEach(coupon -> System.out.println(coupon));

//        companyService.getCompanyCoupons(Category.VACATION).forEach(coupon -> System.out.println(coupon));

//        companyService.getCompanyCoupons(1000).forEach(coupon -> System.out.println(coupon));

//        System.out.println(companyService.getCompanyDetails());

	}

	public void testCustomer() throws Exception {
//    	 CustomerService customerService = (CustomerService) loginManager.login("ping@gmail.com", "55555", ClientType.CUSTOMER);

//    	 customerService.purchaseCoupon(74);

//    	 customerService.getCustomerCoupons().forEach(coupon -> System.out.println(coupon));

//       customerService.getCustomerCoupons(Category.FOOD).forEach(coupon -> System.out.println(coupon));

//       customerService.getCustomerCoupons(1500).forEach(coupon -> System.out.println(coupon));

//       System.out.println(customerService.getCustomerDetails());

	}

}
