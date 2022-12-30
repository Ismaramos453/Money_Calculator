package moneycalculator_v20220913.persistence;

import moneycalculator_v20220913.model.Currency;
import moneycalculator_v20220913.model.ExchangeRate;

public interface ExchangeRateLoader {
    public ExchangeRate exchangerateLoader(Currency from, Currency to);
}