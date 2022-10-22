package com.example.my_application.data;

import com.example.my_application.data.model.LoggedInUser;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Driver;
/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {//очень хорошее приложение
    String [] Drivers_IDs = {"D01", "D01", "D02","D03","D04","D05", "D06","D07","D08","D09","D10",
            "D11","D12","D13","D14","D15","D16","D17","D18","D19","D20","D21","D22","D23","D24","D25"
            ,"D26","D27","D28","D29","D30","D31","D32","D33","D34","D35","D36","D37","D38","D39","D40"};
    public Result<LoggedInUser> login(String username) {
        LoggedInUser Driver = null;
            // TODO: handle loggedInUser authentication
            for (int i = 0; i < 40; ++i) {
                if (username.equals(Drivers_IDs[i])) {
                    Driver =  new LoggedInUser(username);
                    break;
                }
            }
        if (Driver != null) {
            return new Result.Success<>(Driver);
        } else {
            return new Result.Error(new IOException("Error logging in"));
        }
    }


    public void logout() {
        // TODO: revoke authentication
    }
}
// D01	D02	D03	D04	D05	D06	D07	D08	D09	D10	D11	D12	D13	D14	D15	D16	D17	D18	D19	D20	D21	D22	D23	D24	D25	D26	D27	D28	D29	D30	D31	D32	D33	D34	D35	D36	D37	D38	D39	D40