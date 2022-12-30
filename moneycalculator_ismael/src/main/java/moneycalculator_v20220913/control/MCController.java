package moneycalculator_v20220913.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moneycalculator_v20220913.model.Currency;
import moneycalculator_v20220913.model.ExchangeRate;
import moneycalculator_v20220913.model.Money;
import moneycalculator_v20220913.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator_v20220913.view.swing.DialogSwing;
import moneycalculator_v20220913.view.swing.DisplaySwing;

public class MCController implements ActionListener {
    private final DialogSwing dialogSwing;
    private final DisplaySwing displaySwing;
    private final ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService;

    public MCController(DialogSwing dialogSwing,
                        DisplaySwing displaySwing,
                        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService) {
        this.dialogSwing = dialogSwing;
        this.displaySwing = displaySwing;
        this.exchangeRateLoaderFromWebService = exchangeRateLoaderFromWebService;
        this.dialogSwing.registerController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       Money money = this.dialogSwing.getMoney();
       Currency currencyFrom = money.getCurrency();
       Currency currencyTo = this.dialogSwing.getCurrencyTo();
       ExchangeRate exchangeRate = this.exchangeRateLoaderFromWebService.exchangerateLoader(currencyFrom, currencyTo);
       
       this.displaySwing.refreshMoney(new Money(exchangeRate.getRate() * money.getAmount(), currencyTo));
    }
}