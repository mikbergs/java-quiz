/*
*	GameEngine.java
*	Author:	Mikael Bergström
*	Version: 0.1 2016-08-23
*/
import java.io.IOException;
import java.util.*;

public class GameEngine {
	private int nrPlayers;
	private int playerID;
	private Player[] playerArray;
	private ArrayList<Question> questionArray;
	
	public GameEngine(int nrPlayers) throws IOException{
		playerID = 0;
		this.nrPlayers = nrPlayers;
		questionArray = new ArrayList<Question>();
		addPlayers(nrPlayers);
		addQuestions();
		startGame();
		
	}
	public static void main(String[] args) throws IOException{
		System.out.println("Mata hur många spelare ni är, alla spelare får svara på frågorna, så tjuvkika inte");
		Scanner input = new Scanner(System.in);
		GameEngine gameEngine = new GameEngine(input.nextInt());

	}
	
	/*
	 * add the amount of players selected. 
	 * @playerArray - an Array containing the players.
	 * @nrPlayers - the number of players
	 * 
	 */
	public void addPlayers(int nrPlayers){
		playerArray = new Player[nrPlayers];
		Scanner input = new Scanner(System.in);
		System.out.println("Skriv in era namn följt av enter.");
		for (int i = 0; i <	nrPlayers; i++ ){
			playerArray[i] = new Player(input.nextLine());
			System.out.println("Välkommen " + playerArray[i].getName());
		}
	}
	
	/*
	 * Adds questions to the game. 
	 * @ stringArray - Arraylist <String>
	 * @ nrQuestions - int size of the StringArray
	 */
	public void addQuestions() throws IOException{
		FileHandler file = new FileHandler();
		ArrayList<String> stringArray = new ArrayList<>();
		stringArray = file.loadFile();
		int nrQuestions = stringArray.size();
		for (int i = 0; i<nrQuestions; i = i+5){
			questionArray.add(new Question(stringArray.get(i), stringArray.get(i+1),
										stringArray.get(i+2), stringArray.get(i+3),stringArray.get(i+4)));
		}
	}
	
	/*
	 * Prints the score
	 */
	private void endGame() {
		ArrayList<Integer> scoreArray = new ArrayList<Integer>();
		//iterates through the players and add there score in a ArrayList
		for(int i = 0; i < nrPlayers; i++){
			scoreArray.add(playerArray[i].getPoints());
		}
		Collections.sort(scoreArray,Collections.reverseOrder());
		Player winner = playerArray[0];
		if(scoreArray.get(0)==scoreArray.get(1)){
			System.out.println("Oavgjort!");
		}else {
			System.out.println("Spelet över. Grattis " + winner.getName()+ " du vann!");
		}
		System.out.println("----POÄNGTABELL----");
		for(int i = 0; i < nrPlayers; i++){
			System.out.println(i+1 + ". " + playerArray[i].getName()+ " " +scoreArray.get(i)+"p");
			
		}
	}

	public void startGame(){
		Iterator<Question> questionIterator = questionArray.iterator();
		while (questionIterator.hasNext()){
			Question currentQuestion = questionIterator.next();
			//The for loop make sure everybody get's to answer the question.
			for (int i = 0; i < nrPlayers; i++){
				String correctAnswer = printQuestion(currentQuestion);
				fetchAnswer(correctAnswer);
				System.out.println(playerArray[playerID].getName()+":"+playerArray[playerID].getPoints());
				changePlayer();
			}
		}
		endGame();
	}

	/*
	 * Prints the question and the possible answers.
	 * @currentQuestion - a question object.
	 * @ return - the correct answer.
	 */
	private String printQuestion(Question currentQuestion) {
		System.out.print(playerArray[playerID].getName()+ "s tur. ");
		System.out.println(currentQuestion.getQuestion());
		System.out.println(currentQuestion.getChoice1());
		System.out.println(currentQuestion.getChoiceX());
		System.out.println(currentQuestion.getChoice2());
		return currentQuestion.getCorrectAnswer();
	}
	/*
	 * Scans the answer input from the user
	 * @ correctAnswer - string
	 * @ answer - string the users input.
	 * @ input - scanner 
	 */
	private void fetchAnswer(String correctAnswer){
	Scanner input = new Scanner(System.in);
	String answer = input.nextLine();
	answer.toUpperCase();
		if (answer.equals(correctAnswer)){
			System.out.println("Rätt svar!");
			playerArray[playerID].addPoint();
		}
		else if (!(answer.equals("1")||answer.equals("x") || answer.equals("2"))){
			System.out.println("Du kan välja mellan 1, X, och 2. Försök igen.");
			fetchAnswer(correctAnswer);
		}
		else {
			System.out.println("Fel svar.");
		}
	}
	/*
	 * Changes the active player
	 * @ playerID - int
	 * @ nrPlayers - int	
	 */
	public void changePlayer(){
		playerID++;
		playerID = playerID % nrPlayers;
	}
}
