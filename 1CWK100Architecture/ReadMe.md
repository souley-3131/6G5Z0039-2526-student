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
import uk.ac.mmu.game.applicationcode.usecase.play.Provided;
@Component
class Play implements org.springframework.boot.CommandLineRunner, Ordered {

    private final Provided usecase;

    Play(Provided usecase) {
        this.usecase = usecase;
    }


    @Override
    public void run(String... args)  {
        System.out.format("Played Game Id = %d%n", usecase.play());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

# 1CWK100 Game – My Implementation

## 1. Variations and Advanced Features Implemented
- Basic Game: 5x5 board with Red starting from 1 going to 25 and Blue starting at 25 going to 1.
-Wormholes
- Hit rule
- Overshoot rule
- Replay system
- Turn-based game loop

## 2. Design Patterns Used
### Rule Engine Pattern
Explain how rules are applied in order.

### Strategy Pattern (Rules)
I implemented movement as its own seperate Move class allowing me to update my movement logic
in any way later on for example prevemtimg the overshoot.

### Dependency Injection (Spring Boot)
Explain how AppConfig wires dependencies.

## 3. SOLID Principles Followed
### Single Responsibility Principle
Move class' job is to calculate the the Dice's rolls and update the player positions.

### Open/Closed Principle
Rules can be added without modifying existing ones.

### Dependency Inversion Principle
Use cases depend on interfaces, not concrete classes.

## 4. Clean Architecture / Ports & Adapters
Explain:
- Domain model is independent
- Use cases depend on domain
- Infrastructure depends on use cases
- No circular dependencies

## 5. Implementation Summary
Explain where you inserted:
- MoveRule logic
- WormholeRule logic
- HitRule logic
- EndRule logic
- RuleEngine logic
- Game turn loop

## 6. How to Run the Game
Run the `Play` class.



