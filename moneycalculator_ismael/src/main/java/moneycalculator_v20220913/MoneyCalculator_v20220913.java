package moneycalculator_v20220913;

import java.util.List;
import javax.swing.SwingUtilities;
import moneycalculator_v20220913.control.MCController;
import moneycalculator_v20220913.model.Currency;
import moneycalculator_v20220913.model.Money;
import moneycalculator_v20220913.persistence.files.CurrencyLoaderFromFile;
import moneycalculator_v20220913.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator_v20220913.view.swing.DialogSwing;
import moneycalculator_v20220913.view.swing.DisplaySwing;
import moneycalculator_v20220913.view.swing.MoneyCalculatorGUISwing;

public class MoneyCalculator_v20220913 {

    public static void main(String[] args) {
        System.out.println("MoneyCalculator...");
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.currencyLoader();
        
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService();

        DisplaySwing displaySwing = new DisplaySwing(new Money(0.0, currencies.get(0)));
        DialogSwing dialogSwing = new DialogSwing(currencies);
        
        new MCController(dialogSwing, 
                         displaySwing, 
                         exchangeRateLoaderFromWebService);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoneyCalculatorGUISwing(displaySwing, "Money Calculator Display...");
                new MoneyCalculatorGUISwing(dialogSwing, "Money Calculator Display...");        
            }
        });
    }
}