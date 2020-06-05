package btp400.ass2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlgorithmRunnerAppController {
	
	@FXML
	private TextField searchTextField;
	
	@FXML
	private Label countLabel;
	
	@FXML
	private Label timeLabel;
	
	@FXML
	private Label completedInLabel;
	
	@FXML
	private ChoiceBox<String> algorithmsChoiceBox;
	
	@FXML
	private Button runButton;
	
	@FXML
	private Button resetBCButton;
	
	final XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	
	@FXML
	private BarChart<String, Number> algorithmBarChart;
	
	
	/**
	 * Sets up the GUI by setting necessary labels, etc.
	 */
	@FXML
	void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver Loaded!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		countLabel.setText("");
		timeLabel.setText("");
		completedInLabel.setText("");
		
		algorithmsChoiceBox.getItems().addAll("Bubble Sort - Array", "Bubble Sort - Array List", "Parallel Merge Sort - Array", 
				"Parallel Merge Sort - Array List", "Sequential Provided Sort - Array", "Sequential Provided Sort - Array List",
				"Sequential Provided Sort - Linked List", "Concurrent Provided Sort - Array");
		algorithmsChoiceBox.getSelectionModel().select(0);
		
		algorithmBarChart.setTitle("Algorithm Speeds");
		series.setName("Speed");
		algorithmBarChart.getData().add(series);
		resetBCButton.setDisable(true);
		algorithmBarChart.setAnimated(false);
	}
	
	/**
	 * Runs the strategy pattern depending on what algorithm the user chooses. 
	 * Inserts algorithm operation plus the speed at which it executed into the database.
	 * Adds the operation and speed of algorithm on the Bar Chart
	 */
	@FXML
	void onRunButtonAction(ActionEvent event) throws SQLException, InterruptedException, ExecutionException {
		String algorithmChoice = algorithmsChoiceBox.getValue();		
		
		Context context = null;
		Future<Double> algorithmTime = null;
		
		switch(algorithmChoice) {
		case "Bubble Sort - Array":
			context = new Context(new BubbleSortArray());
			break;
		case "Bubble Sort - Array List":
			context = new Context(new BubbleSortArrayList());
			break;
		case "Parallel Merge Sort - Array":
			context = new Context(new ParallelMergeSortArray(3));
			break;
		case "Parallel Merge Sort - Array List":
			context = new Context(new ParallelMergeSortArrayList(3));
			break;
		case "Sequential Provided Sort - Array":
			context = new Context(new SequentialArrayProvidedSort());
			break;
		case "Sequential Provided Sort - Array List":
			context = new Context(new SequentialArrayListProvidedSort());
			break;
		case "Sequential Provided Sort - Linked List":
			context = new Context(new SequentialLinkedListProvidedSort());
			break;
		case "Concurrent Provided Sort - Array":
			context = new Context(new ConcurrentProvidedArray());
			break;
		}
		
		completedInLabel.setText("Completed in:");
		try {
			algorithmTime = Executors.newCachedThreadPool().submit(context);
			timeLabel.setText(String.valueOf(algorithmTime.get()) + "(s)");
			series.getData().add(new XYChart.Data<String, Number>(algorithmChoice, algorithmTime.get()));
			if(resetBCButton.isDisabled()) {
				resetBCButton.setDisable(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection con = connectToDatabase();
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO AlgorithmTimes VALUES (' " + context.getName() + "', " + String.valueOf(algorithmTime.get()) + ")");
		con.close();
	}
	
	/**
	 * Uses the Search function made in the "Search" class to find whatever value was specified in the searchTextField
	 */
	@FXML
	void onSearchButtonAction(ActionEvent event) {
		if(searchTextField.getText().isEmpty()) {
			countLabel.setText("Found: 0");
			return;
		}
		
		int amountFound = Search.search(searchTextField.getText().toUpperCase());
		countLabel.setText("Found: " + amountFound);
	}
	
	/*
	@FXML
	void onDatabaseButtonAction(ActionEvent event) throws IOException, SQLException {
		File fout = new File("database.txt");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		Connection con = connectToDatabase();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT operation, seconds FROM AlgorithmTimes");
		while (rs.next()) {
			String operator = rs.getString("operation");
			double time = rs.getDouble("seconds");
			bw.write(operator + ": " + time);
			bw.newLine();
		}
		con.close();
		bw.close();
	}
	*/
	
	/**
	 * Resets the Bar Chart, removing all information  (clearing the series)
	 */
	@FXML
	void onResetBCButtonAction(ActionEvent event) {
		series.getData().remove(0, series.getData().size());
		resetBCButton.setDisable(true);
	}
	
	/**
	 * Function to connect to the database
	 * @return Connection which is used to run queries
	 */
	public Connection connectToDatabase() {
		Connection connection = null;
		try {
			// connection parameters
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "mymysql.senecacollege.ca";
			String db = "btp400_191a26";
			String user = "btp400_191a26";
			String password = "gsWM@7874";
			int port = 3306;
			String mysqlURL = "jdbc:mysql://" + host + ":" + port + "/" + db;

			// connection attempt
			connection = DriverManager.getConnection(mysqlURL, user, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}

}
