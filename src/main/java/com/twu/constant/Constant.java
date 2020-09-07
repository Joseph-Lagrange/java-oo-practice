package com.twu.constant;

public interface Constant {

    String CommandConfig_Init = "ConfigCommand";

    String Welcome_Message = "Welcome! Please login as:";

    String Relogin_Message = "Please login again as:";

    String User_Login_Message = "Please input username:";

    String User_Command_Choose = "Hello %s! You can:\n";

    String Add_Search_Message = "Please input hot search name:";

    String Vote_Message = "Which hot search do you want to vote for?";

    String Buy_Message = "Which hot search do you want to buy for?";

    String Vote_Tip_Message = "Please input ticket number:";

    String Buy_Tip_Message = "Please input money:";

    String Buy_Rank_Tip_Message = "Please input rank:";

    String Add_Success_Message = "Add hot search successfully";

    String Super_Add_Success_Message = "Super add hot search successfully";

    String Vote_Success_Message = "Vote hot search successfully";

    String Buy_Success_Message = "Buy hot search successfully";

    String Buy_Fail_Message = "Fail to buy hot search";

    String Admin_Login_Message = "Please input password:";

    String Admin_Vaild_Fail_Message = "Password Wrong! User will quit!";

    String Super_Add_Fail_Message = "Should be Admin Identity";

    int User_Command = 1;

    int Admin_Command = 2;

    int Quit_Command = 3;

    int Default_Ticket_Num = 10;

    int User_Authority = 0;

    int Admin_Authority = 1;

    String Admin_Password = "admin";

    int User_Quit_Command = 5;

    int Admin_Quit_Command = 4;

    String Quit_Message = "User %s Quit\n";

    int Supper_Search = 1;
}
