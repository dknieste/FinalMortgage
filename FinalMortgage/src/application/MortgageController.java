package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class MortgageController implements Initializable{
	
	@FXML
	private TextField totalGrossIncome;
	
	@FXML
	private TextField totalMonthlyPayment;
	
	@FXML
	private TextField mortgageInterestRate;
	
	@FXML
	private ComboBox<String> Term;
	
	@FXML
	private TextField downPayment;
	
	@FXML
	private Label mortgageToBeFinanced;
	@FXML
	private Label maxPaymentAllowed;
		
	public void initialize(URL location, ResourceBundle resources) {
		downPayment.setText("0");
		totalGrossIncome.setText("10000");
		totalMonthlyPayment.setText("100");
		mortgageInterestRate.setText("0.01");
		ObservableList<String> years = FXCollections.observableArrayList("10", "15", "30");
		Term.setItems(years);
		Term.setValue("10");


	}
    	
	public double estimateHousingPaymentOnly(double totalGrossIncome){
		double housingPaymentOnly = 0.18*(totalGrossIncome/12);
		return housingPaymentOnly;
	}
	
	public double estimateHousingPlusOtherPayments(double totalGrossIncome, double totalMonthlyPayment){
		double housingPlusOtherPayments = .36*(totalGrossIncome/12) - totalMonthlyPayment;
		return housingPlusOtherPayments;
	}
	
	public double determineMaxPaymentAllowed(double housingPaymentOnly, double housingPlusOtherPayments){
		double maxPaymentAllowed = Math.min(housingPaymentOnly, housingPlusOtherPayments);
		return maxPaymentAllowed;
	}

	public double determineMortgageToBeFinanced(double mortgageInterestRate, double Term, double totalMonthlyPayment, double downPayment){
		double totalMonthsToPay = 12*Term;
		double interestRate = mortgageInterestRate/12;
		double mortgageToBeFinanced;
		if(interestRate == 0){
			mortgageToBeFinanced = -1*(totalMonthsToPay*totalMonthlyPayment);
		}
		else{
			double increasingRate = interestRate + 1;
			mortgageToBeFinanced = (totalMonthlyPayment*(Math.pow(increasingRate,totalMonthsToPay)-1))/((interestRate)*(Math.pow(increasingRate,totalMonthsToPay))) - downPayment;	
		}
		return mortgageToBeFinanced;
	}
	
    @FXML
    private void calculate(ActionEvent calculate){
    	
    	//get all textfield values
    	double totalGrossIncomeD = Double.parseDouble(totalGrossIncome.getText());
    	double totalMonthlyPaymentD = Double.parseDouble(totalMonthlyPayment.getText());
    	double mortgageInterestRateD = Double.parseDouble(mortgageInterestRate.getText());
    	double downPaymentD = Double.parseDouble(downPayment.getText());
    	double TermD = Double.parseDouble(Term.getValue());
    	
    	//Find housing payments
    	double housingPaymentOnlyD = estimateHousingPaymentOnly(totalGrossIncomeD);
    	
    	//Find housing payments + other expenses
    	double housingPlusOtherPaymentsD = estimateHousingPlusOtherPayments(totalGrossIncomeD, totalMonthlyPaymentD);
    	
    	//Find maximum payment allowed
    	double maxPaymentAllowedD = determineMaxPaymentAllowed(housingPaymentOnlyD, housingPlusOtherPaymentsD);
    	
    	double mortgageToBeFinancedD = determineMortgageToBeFinanced(mortgageInterestRateD, TermD, totalMonthlyPaymentD, downPaymentD);
    	String mortgageToBeFinancedS = Double.toString(mortgageToBeFinancedD);
    	
    	DecimalFormat df = new DecimalFormat("#.##");
    	mortgageToBeFinancedD = Double.valueOf(df.format(mortgageToBeFinancedD));
    	mortgageToBeFinanced.setText("$" + mortgageToBeFinancedD);
    	
    	DecimalFormat df2 = new DecimalFormat("#.##");
    	maxPaymentAllowedD= Double.valueOf(df2.format(maxPaymentAllowedD));
    	maxPaymentAllowed.setText("$" + maxPaymentAllowedD);
    	
    }
   
    	
    	
}



