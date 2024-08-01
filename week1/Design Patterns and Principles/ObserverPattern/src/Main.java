public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer mobileApp2 = new MobileApp("MobileApp2");
        Observer webApp = new WebApp("WebApp");

        stockMarket.register(mobileApp1);
        stockMarket.register(mobileApp2);
        stockMarket.register(webApp);

        stockMarket.setStockPrice(100.0);
        stockMarket.setStockPrice(150.0);

        stockMarket.deregister(mobileApp2);
        stockMarket.setStockPrice(200.0);
    }
}