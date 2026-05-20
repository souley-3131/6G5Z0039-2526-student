package uk.ac.mmu.game;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.applicationcode.domainmodel.Game;
import uk.ac.mmu.game.applicationcode.usecase.play.Provided;
import uk.ac.mmu.game.applicationcode.variations.Variation;

@Component
class Play implements org.springframework.boot.CommandLineRunner, Ordered {

    private final Provided usecase;

    Play(Provided usecase) {
        this.usecase = usecase;
    }

    @Override
    public void run(String... args)  {
        new Game(Variation.BASIC).play();
        new Game(Variation.SINGLE_DIE).play();
        new Game(Variation.EXACT_END).play();
        new Game(Variation.HIT).play();
        new Game(Variation.TELEPORT).play();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

