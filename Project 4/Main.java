package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		Button part1 = new Button("Part1");
		Button part2 = new Button("Part2");
		Button part3 = new Button("Part3");
		HBox hbox = new HBox();
		hbox.getChildren().addAll(part1,part2,part3);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		BorderPane border = new BorderPane();
		border.setBottom(hbox);
		
		part1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Project4a(border);
			}
		});
		part2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Project4b(border);
			}
		});
		part3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Project4c(border);
			}
		});
		
		Scene scene = new Scene(border,600,250);
		primaryStage.setTitle("Project 4");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	// It would be so much simpler if I can use the Set in Java util for saving used cards)
	public Image[] shuffle(int cards){
		Image[] deck = new Image[cards];
		int[] taken = new int[cards];
		// Initialize the Decks
		for(int count = 0; count < cards; count++){
			taken[count]= count + 1;
		}
		//Shuffle Method( Every time a random number is gain,it is cross out, then decrease the random max)
		for(int count = 0; count < cards; count++){
			int max = intRand(cards - count) +1;
			for(int count2 = 0; count2 < max; count2++){
				if(taken[count2] == 0){
					max++;
				}
			}
			taken[max-1] = 0;
			deck[count] = new Image("/cardImage/" + max+".png");
		}
		
		return deck;
		
	}

	// This random request to make a random generator is well...random
	public int intRand(int max){
		int last;
	    last = (int) (System.currentTimeMillis() % max);
	    last = (last * 32719 + 3) % 32749;
		
		return last%max;
	}
	
//Launch
	public static void main(String[] args) {
		launch(args);
	}
//Project4a - Randoms 3 Cards
	private void Project4a(BorderPane border) {
		Image[]deck = new Image[52];
		//Deck Shuffler
		deck = shuffle(52);			
		//ImageViewers ( band-aid copypaste compared to Part 2)
		ImageView view0 = new ImageView(deck[0]);
        ImageView view1 = new ImageView(deck[1]);
        ImageView view2 = new ImageView(deck[2]);
        GridPane grid = new GridPane();
        GridPane.setConstraints(view0,1,0);
        GridPane.setConstraints(view1,2,0);
        GridPane.setConstraints(view2,3,0);
        grid.getChildren().addAll(view0,view1,view2);
        border.setTop(grid);
        //Band-aid Solution of preexisting buttons from Part1,Part2,Part3
        border.setBottom(null);
	}
//Project 4b - See All Deck
	private void Project4b(BorderPane border) {
		Image[]deck = new Image[54];
		deck = shuffle(54);
		GridPane grid = new GridPane();
		
		//Imageview - couldve been its own method, but tests is needed.
		ImageView[] view = new ImageView[54];
		for(int count =0; count<54; count++){
			view[count] = new ImageView(deck[count]);
			GridPane.setConstraints(view[count],count%9,(count/9));
			grid.getChildren().add(view[count]);
		}
			
		border.setTop(grid);
        //Band-aid Solution of preexisting buttons from Part1,Part2,Part3
        border.setBottom(null);
	}
//Project 4c -Randoms 4 Cards with a Button
	private void Project4c(BorderPane border) {
		Image[]deck = new Image[52];
		//Deck Shuffler
		deck = shuffle(52);			
		//ImageViewers ( band-aid copypaste compared to Part 2)
		ImageView view0 = new ImageView(deck[0]);
        ImageView view1 = new ImageView(deck[1]);
        ImageView view2 = new ImageView(deck[2]);
        ImageView view3 = new ImageView(deck[3]);
        GridPane grid = new GridPane();
        GridPane.setConstraints(view0,1,0);
        GridPane.setConstraints(view1,2,0);
        GridPane.setConstraints(view2,3,0);
        GridPane.setConstraints(view3,4,0);
        Button button = new Button("Refresh");
        //Refresh Button
        button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Project4c(border);
			}
		});
        grid.getChildren().addAll(view0,view1,view2,view3);
        border.setTop(grid);
        border.setBottom(button);
      
	}
}
