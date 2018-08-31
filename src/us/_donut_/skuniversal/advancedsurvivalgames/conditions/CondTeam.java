package us._donut_.skuniversal.advancedsurvivalgames.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesHook.*;

@Name("AdvancedSurvivalGames - Has Teammate")
@Description("Checks if a player has a teammate.")
@Examples({"if the player has a survival games team:"})
public class CondTeam extends Condition {

    static {
        Skript.registerCondition(CondTeam.class,
                "%offlineplayer% has [a[n]] [[advanced] (survival games|sg)] team[mate]",
                "%offlineplayer% does(n't| not) have [a[n]] [[advanced] (survival games|sg)] team[mate]");
    }

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has survival games team";
    }

    @Override
    public boolean check(Event e) {
        if (player == null) return isNegated();
        return playerManager.getSGPlayer(player.getSingle(e)).hasTeam() != isNegated();
    }
}
