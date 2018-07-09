package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.entity.Player;

public class BedwarsRegister {

    public BedwarsRegister() {
        //Conditions
        Skript.registerCondition(CondInGame.class, "%player% is [playing] in [a] Bedwars game");
        Skript.registerCondition(CondSpectating.class, "%player% is spectating [a] Bedwars game");
        Skript.registerCondition(CondGameExists.class, "[a] Bedwars game [(named|with name)] %string% exists");
        Skript.registerCondition(CondGameRunning.class, "[the] Bedwars game [(named|with name)] %string% is (running|active)");
        Skript.registerCondition(CondGameStartable.class, "[the] Bedwars game [(named|with name)] %string% is (startable|able to start)");

        //Effects
        Skript.registerEffect(EffStartGame.class, "start [the] Bedwars game [(named|with name)] %string%");
        Skript.registerEffect(EffEndGame.class, "(end|stop) [the] Bedwars game [(named|with name)] %string%");
        Skript.registerEffect(EffJoinGame.class, "(make|force) %player% [to] join [the] Bedwars game [(named|with name)] %string%");
        Skript.registerEffect(EffLeaveGame.class, "(make|force) %player% [to] leave [the] Bedwars game [(named|with name)] %string%");

        //Expressions
        Skript.registerExpression(ExprGames.class, String.class, ExpressionType.SIMPLE, "[[the] names of] [all] [the] [existing] Bedwars games");
        Skript.registerExpression(ExprGameOfPlayer.class, String.class, ExpressionType.COMBINED, "[[the] name of] [the] Bedwars game of %player%", "[[the] name of] %player%'s Bedwars game");
        Skript.registerExpression(ExprPlayers.class, Player.class, ExpressionType.COMBINED, "[the] players in [the] Bedwars game [(named|with name)] %string%");
    }
}