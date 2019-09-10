package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	private void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	@FXML
	private ObservableList<Department> obsList;  
	
	@FXML
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializeNodes();
		
	}


	private void inicializeNodes() {
		tableColumId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		Stage stage = (Stage) Main.getScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	public void upDateTableView() {
		if (service == null) {
			throw new IllegalAccessError();
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}

}
