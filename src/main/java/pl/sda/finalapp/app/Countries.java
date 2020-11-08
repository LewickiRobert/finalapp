package pl.sda.finalapp.app;

public enum Countries {
    POLAND("Polska","PL"),
    GERMANY("Niemcy","DE"),
    CHINA("Chiny","CN"),
    USA("USA","US");


    private String countryName;
    private String symbol;

    Countries(String countryName, String symbol){
        this.countryName = countryName;
        this.symbol = symbol;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getSymbol() {
        return symbol;
    }
}
