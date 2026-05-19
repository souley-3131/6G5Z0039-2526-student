# 1CWK100 Clean Architecture Starter Code


The main() method launches the Spring Boot application.

```Java
@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {

        SpringApplication.run(GameApplication.class, args);
	}

}
```

The Spring Boot application scans for components (any class annotated with `@Component`) and configuration classes (any class annotated with `@Configuration`)in the same package and sub-packages of the class with the main() method.

Classes annotated with `@Configuration` are used to define Beans that will be managed by the Spring Boot application. Beans are objects that are instantiated, assembled and  managed by the Spring dependency injection (DI) container.
```Java
@Configuration
public class AppConfig
{

    @Bean
    Required required()
    {
        return new Technology();
    }


    @Bean
    uk.ac.mmu.game.applicationcode.usecase.play.Provided playUseCase(Required required)
    {
        return new uk.ac.mmu.game.applicationcode.usecase.play.UseCase(required);
    }

    @Bean
    uk.ac.mmu.game.applicationcode.usecase.replay.Provided replayUseCase(Required required)
    {
        return new uk.ac.mmu.game.applicationcode.usecase.replay.UseCase(required);
    }

}
```

Classes annotated with `@Component` and that implementing `org.springframework.boot.CommandLineRunner` will be automatically run by the Spring Boot application. Spring Boot will pass any dependencies required into the class constructor.

As we have more than one class implementing CommandLineRunner we also need to implement the Ordered interface to control the order in which they are run.

```Java

# 1CWK100 Game – My Implementation

1. Variations 
- Basic Game: 5x5 board with Red starting from 1 going to 25 and Blue starting at 25 going to 1; for the terminal to be
able to reinact this game.java consists of "newPosition= oldPosition + totalRoll" for Red player and 
"newPosition = oldPosition - totalRoll" for blue player.
Consists of 2 dice to determine how many moves a player takes. The movement for a player is determined in Game.java
"redPos += roll;" and "bluePos -= roll;" is how both players are starting and finishing from opposite places 
in simpler terms its saying red player uses + and blue player uses -. 

-Single Dice: Same size board, same rules applying as basic game just using 1 singular die instead of the sum of 2 dice.

- Exact end: This variotion of the game includes the same goal as basic game but winning is a little tougher since, you
have to land on the exact last panel for example red players will be 25 and blue players 1.
If players are close to finishing and roll a numer higher than the required amount to reach their respective final panel
the player is taken moved back spots by the remainder amount. Ive used an if statement "if(redPos > end)" and 
"if(bluePos < end)" basically asking the question that if the red players position is to end up higher than the board
 to take action and same for if blue player was to end up lower.



-Wormholes
- Hit rule
- Overshoot rule
- Replay system
- Turn-based game loop


### Dependency Injection (Spring Boot)
Explain how AppConfig wires dependencies.


## 4. Clean Architecture / Ports & Adapters
Explain:
- Domain model is independent
- Use cases depend on domain
- Infrastructure depends on use cases
- No circular dependencies

## 5. Implementation Summary
Explain where you inserted:

Two dice: Labelled as d1 and d2 are randomised giving the illusion of rolling using Javas Random class.
The player that rolled, the output of the dice and total sum are presented clearly in a format where the player cant get condused.
This behaviour is implemented in the rollDice() method in Game.java.

Single Die: In Game.java, a single number randomiser needing no sum is used and presented in terminal in the same manner as the 2 dice version.
The roll is labelled using an integer as "d" and uses random. to randomise its value.
The "game" knows which dice is being used simply because of the variation call in Game.java
int roll = (variation == Variation.SINGLE_DIE)
? rollSingleDie("red/blue")
: rollDice("red/blue"
whats being asked here is if the game variation isnt double dice, to play using single dice.

-Board: The board is represented by the board class

-Player movement logic is calculated using "newPosition = oldPosition + totalRoll" and "newPosition = oldPosition - totalRoll"
This is whats creating two opposite movement directions on the same linear board.

-Hit Logic: Ive used an if statement "if(redPos > end)" and 
"if(bluePos < end)" basically asking the question that if the red players position is to end up higher than the board
 to take action and same for if blue player was to end up lower.


- WormholeRule logic
- HitRule logic
- EndRule logic
- RuleEngine logic
- Game turn loop

## 6. How to Run the Game
Run the `Play` class.



