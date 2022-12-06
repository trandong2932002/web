package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.account.BaseAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.account.account.Employee;
import live.cnpm_web.entity.account.account.Manager;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidateAccountUtil {
    public static String validateBasicInformation(String firstname, String lastname, String dob,
                                                  String address, String district, String city) {
        if (firstname.trim().equals("") || lastname.trim().equals("")) {
            return "Nhập lại tên";
        }
        try {
            LocalDate.parse(dob);
        } catch (DateTimeParseException e) {
            return "Nhập lại ngày tháng năm sinh";
        }
        return "";
    }

    public static String validateContactInformation(String email, String phoneNumber,
                                                    String password, String retypePassword) {
        if (email.trim().equals("") || AccountDB.checkEmailExist(email, Customer.class)) {
            return "Nhập lại Email";
        } else if (phoneNumber.trim().equals("") || AccountDB.checkPhoneNumberExist(phoneNumber, Customer.class)) {
            return "Nhập lại Số điện thoại";
        } else if (!password.equals(retypePassword) || password.trim().equals("")) {
            return "Nhập lại mật khẩu";
        }

        return "";
    }

    //
    // authenticate account
    // 1 - customer
    // 2 - employee
    // 3 - manager
    //
    public static ImmutableTriple<String, BaseAccount, Class<?>> validateSignInAccount(String phoneNumber, String password) {
        BaseAccount account = null;
        Class<?> type = null;
        if ((account = AccountDB.authenticate(phoneNumber, password, Customer.class)) != null) {
            type = Customer.class;
        } else if ((account = AccountDB.authenticate(phoneNumber, password, Employee.class)) != null) {
            type = Employee.class;
        } else if ((account = AccountDB.authenticate(phoneNumber, password, Manager.class)) != null) {
            type = Manager.class;
        } else {
            return ImmutableTriple.of("Tài khoản không tồn tại", account, type);
        }
        return ImmutableTriple.of("", account, type);
    }

    public static String validateChangePassword(Customer customer, String oldPassword, String newPassword, String retypeNewPasswword) {
        if (!newPassword.equals(retypeNewPasswword) || newPassword.trim().equals("")) {
            return "Nhập lại mật khẩu mới";
        } else if (!customer.getPassword().equals(oldPassword)) {
            return "Nhập lại mật khẩu cũ";
        } else if (customer.getPassword().equals(newPassword)) {
            return "Mật khẩu mới giống mật khẩu cũ";
        } else {
            return "";
        }
    }
}
