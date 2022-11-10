package courses;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class COURSES extends Application {
// object from class client.

    client z = new client();

    @Override
    public void start(Stage s) throws ClassNotFoundException, SQLException {
// scene of login  that take username and password of the user .
        Button PSignIN = new Button("Signin");

        ImageView img = new ImageView("ballpen-blur-close-up-461077.jpg");

        img.setFitHeight(500);
        img.setFitWidth(500);

        Pane SignIN = new Pane();
        PSignIN.setLayoutX(230);
        PSignIN.setLayoutY(240);
        PSignIN.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, FontPosture.REGULAR, 15));
        //PSignIN.setStyle("-fx-text-fill:White;" + "-fx-background-color:BLACK;");
        SignIN.getChildren().addAll(img, PSignIN);
        Scene sc = new Scene(SignIN, 500, 500);
        s.setScene(sc);
        s.setTitle("SIGNIN");
        s.show();
        PSignIN.setOnAction(j -> {

            s.close();

            Stage PrimaryStage = new Stage();

            HBox H = new HBox(10);
            HBox H1 = new HBox(10);
            VBox v = new VBox(45);
            FlowPane o = new FlowPane();
            Pane p = new Pane();
            // background of login
            ImageView m = new ImageView(new Image("bacj.png"));

            m.setFitWidth(700);
            m.setFitHeight(700);

            Label login = new Label("Login ");
            Label error = new Label("enter the empty username or password");
            Label username = new Label("Username");
            TextField user = new TextField();
            user.setPromptText("Enter your username");
            Label password = new Label("Password");
            PasswordField pass = new PasswordField();
            pass.setPromptText("Enter your password");

            // The radiobutton create enables the user to create new account. 
            RadioButton Create = new RadioButton("Create New Account");

            Button log = new Button("Login");
            Label lfaield = new Label();
            login.setFont(Font.font(50));
            // color of text
            login.setTextFill(Color.BLUE);
            username.setTextFill(Color.CORAL);
            password.setTextFill(Color.CORAL);
            Create.setTextFill(Color.CORAL);
            // position of panes
            H.setAlignment(Pos.CENTER);
            H1.setAlignment(Pos.CENTER);
            v.setAlignment(Pos.CENTER);
            // distance between pane and scene.
            o.setPadding(new Insets(150, 250, 300, 180));
            // Button action of create that open the scene of creation. 
            Create.setOnAction(e -> {
                if (Create.isSelected()) {

                    start();
                }
            });

            log.setOnAction(e -> {
                // Execption handling of login .
                if (user.getText().equals("") && pass.getText().equals("")) {
                    error.setText("Enter your  name and password");
                    v.getChildren().add(error);
                    error.setTextFill(Color.RED);
                } else if (user.getText().equals("")) {
                    error.setText("Enter your name");
                    v.getChildren().add(error);
                    error.setTextFill(Color.RED);
                } else if (pass.getText().equals("")) {
                    error.setText("Enter your password ");
                    v.getChildren().add(error);
                    error.setTextFill(Color.RED);
                } else {

                    try {
                        // check that has account before or not.
                        String qury = "SELECT * FROM `CREATEACCOUNT` WHERE USERNAME  =? AND password =?";
                        Connection conn = DATABASE1.getconnection();
                        Statement stat = conn.createStatement();
                        PreparedStatement preparedStmt = conn.prepareStatement(qury);
                        preparedStmt.setString(1, user.getText());
                        preparedStmt.setString(2, pass.getText());
                        ResultSet rset = preparedStmt.executeQuery();
                        if (rset.next()) {
                            // open scene of home 
                            When_Login(PrimaryStage);
                            //clear username and passwoed when return to login.
                            user.setText("");
                            pass.setText("");
                            //close scene of login.
                            PrimaryStage.close();

                        } else {
                            // when enter username and password error.
                            lfaield.setText("Login failed");
                            lfaield.setTextFill(Color.RED);
                        }
                        preparedStmt.close();
                        rset.close();

                    } catch (SQLException | ClassNotFoundException l) {

                        // Logger.getLogger(.class.getName()).log(Level.SEVERE, null, l);
                        System.err.println(l.getMessage());

                    }
                }

            });
            // add nodes to panes .
            H1.getChildren().addAll(password, pass);
            H.getChildren().addAll(username, user);
            v.getChildren().addAll(login, H, H1, lfaield, Create, log);
            // o : flowpane.
            o.getChildren().addAll(v);
            //p:pane.
            p.getChildren().addAll(m, o);

            Scene sc_1 = new Scene(p, 700, 700);

            PrimaryStage.setTitle("Login");
            PrimaryStage.setScene(sc_1);
            PrimaryStage.show();

        });
    }
    // Function that  create new account.

    public void start() {
        Label error = new Label("enter the empty username or password");
        Stage m = new Stage();
        Label correct = new Label("enter Repetedpossword coorect");
        GridPane p = new GridPane();
        p.setVgap(30);
        p.setHgap(30);
        Label Firstname = new Label("Firstname");
        TextField name = new TextField();
        Label password = new Label("Password");
        PasswordField pass = new PasswordField();
        Label repeat = new Label("Repassword");
        PasswordField RE = new PasswordField();
        Label phone = new Label("Phonenumber");
        TextField number = new TextField();
        Button b = new Button("create");
        //add nodes to gridpane.
        p.addColumn(0, Firstname, password, repeat, phone);
        p.addColumn(1, name, pass, RE, number);
        p.add(b, 1, 5);
        //background color.
        p.setStyle("-fx-background-color:purple;");
        // color of text.
        Firstname.setTextFill(Color.WHITE);
        password.setTextFill(Color.WHITE);
        phone.setTextFill(Color.WHITE);

        name.setPromptText("Enter your name");
        pass.setPromptText("Enter your password");
        RE.setPromptText("Enter your repeat");
        number.setPromptText("Enter your phone");
        //position of panes and button 
        p.setAlignment(Pos.CENTER);
        b.setAlignment(Pos.CENTER_RIGHT);
        // 
        b.setOnAction(e -> {
            // Exception handling of creation function.
            if (name.getText().equals("") || pass.getText().equals("") || RE.getText().equals("")
                    || number.getText().equals("")) {
                p.add(error, 2, 5);
                error.setTextFill(Color.BLACK);
            } else {

                b.setDisable(false);
                try {

                    if (pass.getText().equals(RE.getText()) && name.getText().matches("[a-z]+")
                            && number.getText().matches("[0-9]+") && number.getText().length() == 11) {

                        Connection conn = DATABASE1.getconnection();
                        Statement stat = conn.createStatement();
                        ResultSet resultSet = stat.executeQuery("select *  from CREATEACCOUNT  where USERNAME  = '" + name.getText() + "'");

                        if (resultSet.next()) {
                            System.out.println("error");

                        } else {

                            PreparedStatement ss;
                            ss = conn.prepareStatement(" INSERT INTO CREATEACCOUNT(USERNAME, password ,repeatpassword, phonenumber) VALUES (?,?,?,?)");
                            ss.setString(1, name.getText());
                            ss.setString(2, pass.getText());
                            ss.setString(3, RE.getText());
                            ss.setString(4, number.getText());

                            ss.execute();
                            conn.close();
                            m.close();
                        }
                    } else if (!name.getText().matches("[a-z]+")) {
                        correct.setText("Enter Your name correct");
                        p.add(correct, 2, 0);
                        correct.setTextFill(Color.RED);
                    } else if (!pass.getText().equals(RE.getText())) {

                        p.add(correct, 2, 2);
                        correct.setTextFill(Color.RED);
                    } else if (!number.getText().matches("[0-9]+")) {

                        correct.setText("Enter Your phoneNumber correct");
                        p.add(correct, 2, 3);
                        correct.setTextFill(Color.RED);

                    } else if (number.getText().length() < 11) {

                        correct.setText("enter Your Number of  correct");
                        p.add(correct, 2, 3);
                        correct.setTextFill(Color.RED);

                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    //    Logger.getLogger(COURSES.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println(ex.getMessage());

                }

            }
        });

        Scene s = new Scene(p, 400, 400);
        m.setTitle("Create Account");
        m.setScene(s);
        m.show();

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
        // Create tables of database .
        stat.executeUpdate("CREATE TABLE  IF NOT EXISTS CREATEACCOUNT(" + "USERNAME varchar(15) NOT NULL," + "password varchar(15) NOT NULL,"
                + " repeatpassword varchar(15) NOT NULL,"
                + "phonenumber varchar(15) NOT NULL,"
                + " PRIMARY KEY(USERNAME));");

        Statement stat_2 = conn.createStatement();
        stat_2.executeUpdate("CREATE TABLE  IF NOT EXISTS ADDCOURSES(" + "NAME varchar(15) NOT NULL," + "AGE int NOT NULL," + "courseandprice varchar(20) NOT NULL, " + "ID varchar(20) NOT NULL,"
                + " gender varchar(15) NOT NULL,"
                + "phonenumber varchar(15) NOT NULL," + "PRIMARY KEY(ID),"
                + "date varchar(15) NOT NULL) ;");

        launch(args);

    }

    // The main application of system .
    public static void When_Login(Stage po) throws SQLException, ClassNotFoundException {
        Stage s = new Stage();
        BorderPane myApp = new BorderPane();
        // position between pane and scene.
        myApp.setPadding(new Insets(10, 0, 0, 0));

        HBox home_ph = new HBox();

        Label welcome = new Label("Welcome  to Course Management System");
        VBox desgin_l = new VBox();
        ImageView photo1 = new ImageView(new Image("login.png"));
        ImageView photo2 = new ImageView(new Image("images.png"));
        ImageView photo3 = new ImageView(new Image("567.png"));
        ImageView photo4 = new ImageView(new Image("1234.png"));
        ImageView photo5 = new ImageView(new Image("delete.png"));
        ImageView photo6 = new ImageView(new Image("pp.jpg"));
        ImageView photo7 = new ImageView(new Image("back.jpg"));
        //determined of dimention of photo.
        photo1.setFitHeight(100);
        photo1.setFitWidth(100);

        photo2.setFitHeight(25);
        photo2.setFitWidth(25);

        photo3.setFitHeight(25);
        photo3.setFitWidth(25);

        photo4.setFitHeight(25);
        photo4.setFitWidth(25);

        photo5.setFitHeight(25);
        photo5.setFitWidth(25);

        photo6.setFitHeight(25);
        photo6.setFitWidth(25);

        photo7.setFitHeight(25);
        photo7.setFitWidth(25);

        welcome.setFont(Font.font(40));

        // main nodes of system.
        ToggleGroup group = new ToggleGroup();
        ToggleButton HOME = new ToggleButton("HOME", photo2);
        ToggleButton ADD_CLIENT = new ToggleButton("ADD CLIENT", photo3);
        ToggleButton SEARCH = new ToggleButton("SEARCH", photo4);
        ToggleButton DROP = new ToggleButton("DROP", photo5);
        ToggleButton EDIET = new ToggleButton("EDIET", photo6);
        ToggleButton BACK = new ToggleButton("BACK", photo7);

        HOME.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        ADD_CLIENT.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        SEARCH.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        DROP.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        EDIET.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        BACK.setStyle("-fx-text-fill:White;" + "-fx-background-color:DARKCYAN;");
        //choose one button of them.
        HOME.setToggleGroup(group);
        ADD_CLIENT.setToggleGroup(group);
        SEARCH.setToggleGroup(group);
        DROP.setToggleGroup(group);
        EDIET.setToggleGroup(group);
        BACK.setToggleGroup(group);
        home_ph.setSpacing(50);
        desgin_l.setSpacing(10);
        // add button to panes
        desgin_l.getChildren().addAll(HOME, ADD_CLIENT, SEARCH, DROP, EDIET, BACK);
        home_ph.getChildren().addAll(photo1, welcome);
        // home_ph : HBox
        home_ph.setSpacing(300);
        // design_1 : VBox
        desgin_l.setSpacing(20);
        // myApp:BorderPane
        myApp.setTop(home_ph);
        myApp.setLeft(desgin_l);
        desgin_l.setStyle("-fx-background-color:DARKCYAN;");
        myApp.setStyle("-fx-background-color:CADETBLUE;");

        VBox center = new VBox(20);
        center.setStyle("-fx-background-color:SILVER;");
        FlowPane newLine = new FlowPane();
        newLine.setHgap(110);
        newLine.setVgap(20);
        newLine.setMaxWidth(500);

        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {

            if (group.getSelectedToggle().equals(HOME)) {
                // function of disable for not allowed to press the same button twice.
                // HOME
                HOME.setDisable(true);
                ADD_CLIENT.setDisable(false);
                SEARCH.setDisable(false);
                DROP.setDisable(false);
                EDIET.setDisable(false);
                BACK.setDisable(false);

                center.getChildren().clear();
                myApp.setCenter(center);
                // backgroud of home.
                ImageView img = new ImageView("javaaaa.png");
                // determine of dimention.
                img.setFitHeight(1000);
                img.setFitWidth(1000);

                Pane pane = new Pane();
                pane.getChildren().addAll(img);
                //center: VBox.
                center.getChildren().addAll(pane);
                center.setPadding(new Insets(0));

                myApp.setCenter(center);

            } else if (group.getSelectedToggle().equals(ADD_CLIENT)) {
                center.getChildren().clear();
                myApp.setCenter(center);
                //ADD_CLIENT
                HOME.setDisable(false);
                ADD_CLIENT.setDisable(true);
                SEARCH.setDisable(false);
                DROP.setDisable(false);
                EDIET.setDisable(false);
                BACK.setDisable(false);
                FlowPane a = new FlowPane(20, 30);

                FlowPane m = new FlowPane(20, 20);

                FlowPane K = new FlowPane(10, 10);
                Button Add = new Button("ADD");
                Add.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, FontPosture.REGULAR, 15));
                Label name = new Label("Name");
                Label age = new Label("Age");
                Label id = new Label("ID");
                Label phonenumber = new Label("Phone number");
                name.setTextFill(Color.WHITE);
                age.setTextFill(Color.WHITE);
                id.setTextFill(Color.WHITE);
                phonenumber.setTextFill(Color.WHITE);

                DatePicker Date = new DatePicker(LocalDate.now());
                Date.setPromptText("enter  date");
//    final Callback<DatePicker,DateCell>dayCellFactory=(DatePicker)->new DateCell(){
//        public void uptadeItem(LocalDate item,boolean empty){
//            super.updateItem(item, empty);
//            if(item.isBefore(LocalDate.now())){
//              
//            setDisable(true);
//            setStyle("-fx-background-color:RED;");
//                
//            }
//            
//        }
//        
//    };
//  Date.setDayCellFactory(dayCellFactory);
                LocalDate x = Date.getValue();

                TextField n = new TextField();
                TextField A = new TextField();
                TextField i = new TextField();
                TextField p = new TextField();
                n.setPromptText("Enter your name");
                A.setPromptText("Enter your age");
                i.setPromptText("Enter your id");
                p.setPromptText("Enter your phone number");
                ComboBox s2 = new ComboBox();
                s2.getItems().addAll("Java   100$", "Python   200$", "c++   150$", "Javascript   90$", "c#   250$",
                        "css  300$", "Html   287$");
                s2.setPromptText("Select your course");
                RadioButton Female = new RadioButton("Female");
                RadioButton Male = new RadioButton("Male");
                Female.setTextFill(Color.WHITE);
                Male.setTextFill(Color.WHITE);
                ToggleGroup v = new ToggleGroup();
                Female.setToggleGroup(v);
                Male.setToggleGroup(v);
                a.setOrientation(Orientation.VERTICAL);
                a.getChildren().addAll(name, age, id, phonenumber, Female);
                ImageView photo = new ImageView(new Image("picc.png"));
                photo.setFitHeight(900);
                photo.setFitWidth(900);
                Pane img = new Pane();

                Male.setPadding(new Insets(0, 0, 0, 90));
                Female.setPadding(new Insets(0, 0, 0, 90));

                m.setOrientation(Orientation.VERTICAL);
                m.getChildren().addAll(n, A, i, p, Male, s2, Date, Add);
                K.setOrientation(Orientation.HORIZONTAL);
                K.getChildren().addAll(a, m);
                K.setAlignment(Pos.CENTER);
                K.setPadding(new Insets(150, 10, 400, 180));
                img.getChildren().addAll(photo, K);

                center.getChildren().addAll(img);
                Add.setOnAction(z -> {
                    if (n.getText().equals("") || A.getText().equals("") || i.getText().equals("") || p.getText().equals("")
                            || !Female.isSelected() && !Male.isSelected()) {

                        Label error = new Label("please Enter The empty checkboxes");
                        K.getChildren().add(error);
                        error.setTextFill(Color.RED);
                    } else {

                        if (n.getText().matches("[a-z]+") && p.getText().matches("[0-9]+") && p.getText().length() == 11) {

                            if (Male.isSelected()) {
                                try {
                                    client.insert_toCours(n.getText(), A.getText(), Male.getText(), (String) s2.getValue(),
                                            p.getText(), i.getText(), x.toString());
                                } catch (ClassNotFoundException | SQLException ex) {

                                    System.err.println(ex.getMessage());
                                }
                            } else {
                                try {
                                    client.insert_toCours(n.getText(), A.getText(), Female.getText(),
                                            (String) s2.getValue(), p.getText(), i.getText(), x.toString());
                                } catch (ClassNotFoundException | SQLException ex) {

                                    System.err.println(ex.getMessage());
                                }

                            }
                        } else {

                            if (!n.getText().matches("[a-z]+")) {
                                Label correct = new Label();

                                correct.setText("Enter Your name correct");
                                K.getChildren().add(correct);
                                correct.setTextFill(Color.RED);
                            } else if (!p.getText().matches("[0-9]+")) {
                                Label correct = new Label();
                                correct.setText("Enter Your phoneNumber correct");
                                K.getChildren().add(correct);
                                correct.setTextFill(Color.RED);

                            } else if (p.getText().length() < 11) {
                                Label correct = new Label();
                                correct.setText("enter Your Number   correct");
                                K.getChildren().add(correct);
                                correct.setTextFill(Color.RED);

                            }

                        }

                    }
                });

            } else if (group.getSelectedToggle().equals(SEARCH) && SEARCH.isSelected()) {

                center.getChildren().clear();
                myApp.setCenter(center);
                HOME.setDisable(false);
                ADD_CLIENT.setDisable(false);
                SEARCH.setDisable(true);
                DROP.setDisable(false);
                EDIET.setDisable(false);
                BACK.setDisable(false);

                Label enter_id = new Label("Enter id");
                TextField enter_IDt = new TextField();
                Button Search = new Button("Search");

                Label Name = new Label(" Name");
                Name.setTextFill(Color.BLACK);
                Name.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
                Label Age = new Label("Age");
                Age.setTextFill(Color.BLACK);
                Age.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
                Label Course = new Label("Course and price");
                Course.setTextFill(Color.BLACK);
                Course.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
                Label Gender = new Label("Gender");
                Gender.setTextFill(Color.BLACK);
                Gender.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
                Label phoneNumber = new Label("PhoneNumber");
                phoneNumber.setTextFill(Color.BLACK);
                phoneNumber.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));

                Label ID = new Label("ID");
                ID.setTextFill(Color.BLACK);
                ID.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));

                HBox header = new HBox(70);
                header.getChildren().addAll(Name, Age, Course, Gender, phoneNumber, ID);
                HBox h_search = new HBox(30);
                h_search.getChildren().addAll(enter_id, enter_IDt, Search);

                VBox vbox = new VBox(30);
                vbox.getChildren().addAll(h_search, header);

                center.setPadding(new Insets(50));
                center.getChildren().addAll(vbox, newLine);
                Search.setOnAction(e -> {
                    try {
                        Label m = new Label(client.search(enter_IDt.getText()));
                        m.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
                        vbox.getChildren().add(m);;
                    } catch (SQLException | ClassNotFoundException lop) {
                        System.err.println(lop.getMessage());

                    }

                });
            } else if (group.getSelectedToggle().equals(DROP)) {
                HOME.setDisable(false);
                ADD_CLIENT.setDisable(false);
                SEARCH.setDisable(false);
                DROP.setDisable(true);
                EDIET.setDisable(false);
                BACK.setDisable(false);
                center.getChildren().clear();
                myApp.setCenter(center);
                Label enter_id = new Label("Enter id");
                TextField enter_IDt = new TextField();
                Button drop = new Button("Drop");
                HBox h_drop = new HBox(30);
                h_drop.getChildren().addAll(enter_id, enter_IDt, drop);
                center.setPadding(new Insets(50));
                center.getChildren().addAll(h_drop);
                drop.setOnAction(x -> {
                    if (enter_IDt.getText().equals("")) {
                        Label l6 = new Label("Enter the id");
                        h_drop.getChildren().add(l6);
                        l6.setTextFill(Color.RED);
                    } else {

                        try {

                            Label l6 = new Label(client.drop(enter_IDt.getText()));
                            h_drop.getChildren().add(l6);
                            l6.setTextFill(Color.RED);

                        } catch (SQLException | ClassNotFoundException lop) {
                            System.err.println(lop.getMessage());

                        }
                    }
                });
            } else if (group.getSelectedToggle().equals(EDIET)) {
                center.getChildren().clear();
                HOME.setDisable(false);
                ADD_CLIENT.setDisable(false);
                SEARCH.setDisable(false);
                DROP.setDisable(false);
                EDIET.setDisable(true);
                BACK.setDisable(false);

                BorderPane EDEIT_P = new BorderPane();
                myApp.setPadding(new Insets(10, 0, 0, 0));
                myApp.setStyle("-fx-background-color:#c28d8f");

                ListView list = new ListView();
                list.setOrientation(Orientation.HORIZONTAL);
                list.setMaxSize(300, 30);
                list.getItems().addAll("Name", "Course", "PhoneNumber");
                EDEIT_P.setTop(list);
                myApp.setCenter(EDEIT_P);
                //  VBox EDEIT_V = new VBox(20);

                list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

              //          if (null != newValue) {
                            switch (newValue) {
                                case "Name":

                                    center.getChildren().clear();

                                    HBox NAME_HBox = new HBox(10);
                                    Label NAME_LABEL = new Label("Enter your name to Edeit");
                                    TextField NAME_TextField = new TextField();
                                    NAME_TextField.setPromptText("Enter your name");
                                    HBox ID_HBox = new HBox(10);
                                    Label ID_LABEL = new Label("Enter your ID to Edeit");
                                    TextField ID_TextField = new TextField();
                                    ID_TextField.setPromptText("Enter your ID");
                                    Button NAME_Button = new Button("EDIET");
                                    Label error = new Label();

                                    NAME_HBox.getChildren().addAll(NAME_LABEL, NAME_TextField);
                                    ID_HBox.getChildren().addAll(ID_LABEL, ID_TextField);
                                    VBox NAME_VBox = new VBox(10);
                                    NAME_HBox.setAlignment(Pos.CENTER);
                                    ID_HBox.setAlignment(Pos.CENTER);
                                    NAME_VBox.setAlignment(Pos.CENTER);
                                    NAME_VBox.setStyle("-fx-background-color:#008080");

                                    NAME_VBox.getChildren().addAll(NAME_HBox, ID_HBox, NAME_Button, error);
                                    NAME_Button.setOnAction(e -> {
                                        if (NAME_TextField.getText().equals("") || ID_TextField.getText().equals("")) {

                                            error.setTextFill(Color.BLACK);
                                            error.setText("Enter the empty checkboxes");
                                        } else {

                                            NAME_Button.setDisable(true);

                                            try {

                                                if (NAME_TextField.getText().matches("[a-z]+")) {

                                                    Label result = new Label(client.edite_name(NAME_TextField.getText(), ID_TextField.getText()));
                                                    NAME_HBox.getChildren().add(result);

                                                } else if (!NAME_TextField.getText().matches("[a-z]+")) {
                                                    error.setText("Enter Your name correct");
                                                }
                                            } catch (SQLException | ClassNotFoundException k) {
                                                System.err.println(k.getMessage());
                                            }
                                        }
                                    });
                                    center.getChildren().addAll(NAME_VBox);
                                    center.setPadding(new Insets(0));
                                    EDEIT_P.setCenter(center);
                                    myApp.setCenter(EDEIT_P);

                                    break;

                                case "Course": {
                                    center.getChildren().clear();

                                    Label CHOOSE_COURSE = new Label("CHOOSE YOUR COURSE");
                                    HBox Course_HBox = new HBox(10);
                                    ComboBox Course_ComboBox = new ComboBox();
                                    Course_ComboBox.getItems().addAll("Java   100$", "Python   200$",
                                            "c++   150$", "Javascript   90$", "c#   250$",
                                            "css  300$", "Html   287$");
                                    Button Course_Button = new Button("EDIET");
                                    HBox CID_HBox = new HBox(10);
                                    Label CID_LABEL = new Label("Enter your ID to Edeit");
                                    TextField CID_TextField = new TextField();
                                    CID_TextField.setPromptText("Enter your ID");
                                    Course_HBox.getChildren().addAll(CHOOSE_COURSE, Course_ComboBox);
                                    VBox Course_VBox = new VBox(10);
                                    Course_HBox.setAlignment(Pos.CENTER);
                                    CID_HBox.setAlignment(Pos.CENTER);
                                    Course_VBox.setAlignment(Pos.CENTER);
                                    Course_VBox.setStyle("-fx-background-color:#F08080");

                                    CID_HBox.getChildren().addAll(CID_LABEL, CID_TextField);
                                    Course_VBox.getChildren().addAll(Course_HBox, CID_HBox, Course_Button);
                                    Course_Button.setOnAction(e -> {
                                        if (CID_TextField.getText().equals("")) {
                                            Label error1 = new Label();
                                            error1.setTextFill(Color.BLACK);
                                            error1.setText("Enter the empty checkboxes");

                                            Course_VBox.getChildren().add(error1);
                                        } else {
                                            try {
                                                Label result = new Label(client.edite_course((String) Course_ComboBox.getValue(), CID_TextField.getText()));

                                                Course_HBox.getChildren().add(result);
                                            } catch (SQLException | ClassNotFoundException k) {
                                                System.err.println(k.getMessage());
                                            }
                                        }
                                    });
                                    center.getChildren().addAll(Course_VBox);
                                    center.setPadding(new Insets(0));
                                    EDEIT_P.setCenter(center);
                                    myApp.setCenter(EDEIT_P);

                                    break;

                                }
                                case "PhoneNumber": {
                                    center.getChildren().clear();

                                    HBox PhoneNumber_HBox = new HBox(10);
                                    Label PhoneNumber_LABEL = new Label("Enter your PhoneNumber to Edeit");
                                    TextField PhoneNumber_TextField = new TextField();
                                    PhoneNumber_TextField.setPromptText("Enter your PhoneNumber");
                                    HBox PID_HBox = new HBox(10);
                                    Label PID_LABEL = new Label("Enter your ID to Edeit");
                                    TextField PID_TextField = new TextField();
                                    PID_TextField.setPromptText("Enter your Id");
                                    Button PhoneNumber_Button = new Button("EDIET");
                                    VBox PhoneNumber_VBox = new VBox(10);
                                    Label correct = new Label();
                                    PhoneNumber_HBox.setAlignment(Pos.CENTER);
                                    PID_HBox.setAlignment(Pos.CENTER);
                                    PhoneNumber_VBox.setAlignment(Pos.CENTER);
                                    PhoneNumber_VBox.setStyle("-fx-background-color:#5F9EA0");
                                    PhoneNumber_HBox.getChildren().addAll(PhoneNumber_LABEL, PhoneNumber_TextField);
                                    PID_HBox.getChildren().addAll(PID_LABEL, PID_TextField);
                                    PhoneNumber_VBox.getChildren().addAll(PhoneNumber_HBox, PID_HBox, PhoneNumber_Button, correct);
                                    PhoneNumber_Button.setOnAction(e -> {
                                        if (PhoneNumber_TextField.getText().equals("") || PID_TextField.getText().equals("")) {

                                            correct.setTextFill(Color.BLACK);
                                            correct.setText("Enter Your phoneNumber correct");
                                        } else {

                                            PhoneNumber_Button.setDisable(true);

                                            try {
                                                if (PhoneNumber_TextField.getText().matches("[0-9]+") && PhoneNumber_TextField.getText().length() == 11) {
                                                    Label result = new Label(client.edite_phoneNumber(PhoneNumber_TextField.getText(), PID_TextField.getText()));
                                                  
                                                         
                                                       PhoneNumber_HBox.getChildren().add(result);
                                                } else if (!PhoneNumber_TextField.getText().matches("[0-9]+")) {
                                                    correct.setText("Enter Your phoneNumber correct");

                                                }
                                            } catch (SQLException | ClassNotFoundException w) {
                                                System.err.println(w.getMessage());
                                            }
                                        }
                                    });

                                    center.getChildren().addAll(PhoneNumber_VBox);
                                    center.setPadding(new Insets(0));
                                    EDEIT_P.setCenter(center);
                                    myApp.setCenter(EDEIT_P);
                                 

                                    break;

                                }
                            }
                        }
                    
                }
                );
            } else if (group.getSelectedToggle().equals(BACK)) {

                po.show();
                s.close();

            }

        });

        Scene scene = new Scene(myApp, 1000, 1000);

        s.setTitle("COURSES");
        s.setScene(scene);
        s.show();

    }

}
