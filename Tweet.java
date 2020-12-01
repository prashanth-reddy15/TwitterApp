import java.util.*;
import java.io.*;
public class Tweet {
    private final Map<String, Integer> map = new TreeMap<String, Integer>();
    private String text;
	
    private Tweet() {}
	
	//to store the tweet input 
    public void setTweet(String text){
        this.text = text;
    }
	
	//to get the tweet
    public String getTweet() {
        return text;
    }
	
	//This Function is for  Parsing the Tweet token by token and to extract the # in the tweet and store the #word in a map.
    private void parse(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith("#")) {
                Integer count = map.get(token);
                if ( count == null )
                {
                    count = 0;
                }
                map.put(token, count + 1);
            }
        }
    }
	
	// This Function is for Displaying the Top 10 Tweets.
    private  void getCount() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return -(e1.getValue().compareTo(e2.getValue()));
            }
        });
		
        System.out.println("Top 10 tweets are:");
        int count = 0;
		
        for( Map.Entry<String,Integer> m : list){
            if(count>10) return;
            System.out.println(m.getKey());
            count++;
        }

    }
	
	// Main Method in which tweet input is being taken.
    public static void main(String [] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of tweets you want to input:");
        int n = Integer.parseInt(bufferedReader.readLine());
        Tweet tweet = new Tweet();
        if (n <= 0) 
		{
		System.out.println("You Entered No Tweets:");
		}
        else
        {
            while (n-- > 0) {
                System.out.println("Enter the Tweet:");
                String inputTweet = bufferedReader.readLine();
                tweet.setTweet(inputTweet);
                String s = tweet.getTweet();
                tweet.parse(s);
            }
            tweet.getCount();
			
        }
    }
}