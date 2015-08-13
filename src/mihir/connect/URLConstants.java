package mihir.connect;

public class URLConstants {
	
	public final static String BASIC_URL = "https://translate.google.co.in/#en/mr/";
	private StringBuilder URLWithQuery = new StringBuilder(BASIC_URL);
	
	
	
	public StringBuilder getURLWithQuery() {
		return URLWithQuery;
	}
	public void setURLWithQuery(String query) {
		this.URLWithQuery = this.URLWithQuery.append("&q="+query);
	}

}
