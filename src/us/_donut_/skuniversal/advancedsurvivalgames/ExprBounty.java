package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.util.coll.CollectionUtils;
import e.Game;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Bounty")
@Description("Returns the bounty of a player.")
@Examples({"send \"Your bounty: %survival games bounty of player%\""})
public class ExprBounty extends SimpleExpression<Number> {

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player= (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the survival games bounty of player" + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{Game.getPlayerManager().getSGPlayer(player.getSingle(e)).getBounty()};
        }else{
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
            Number bountyChange = (Number)delta[0];
            Number currentBounty = Game.getPlayerManager().getSGPlayer(player.getSingle(e)).getBounty();
            if (mode == Changer.ChangeMode.SET) {
                Game.getPlayerManager().getSGPlayer(player.getSingle(e)).setBounty(bountyChange.intValue());
            } else if (mode == Changer.ChangeMode.ADD) {
                Game.getPlayerManager().getSGPlayer(player.getSingle(e)).setBounty(currentBounty.intValue()+bountyChange.intValue());
            } else if (mode == Changer.ChangeMode.REMOVE) {
                Game.getPlayerManager().getSGPlayer(player.getSingle(e)).setBounty(currentBounty.intValue()-bountyChange.intValue());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}
