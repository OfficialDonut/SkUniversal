package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.util.coll.CollectionUtils;
import e.SGPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesHook.*;

@Name("AdvancedSurvivalGames - Bounty")
@Description("Returns the bounty of a player.")
@Examples({"send \"Your bounty: %survival games bounty of player%\""})
public class ExprBounty extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprBounty.class, Number.class, ExpressionType.COMBINED,
                "[the] [[advanced] (survival games|sg)] bounty of %offlineplayer%",
                "%offlineplayer%'s [[advanced] (survival games|sg)] bounty");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player= (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the survival games bounty of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{playerManager.getSGPlayer(player.getSingle(e)).getBounty()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (player.getSingle(e) == null) return;
        int bountyChange = ((Number) delta[0]).intValue();
        SGPlayer sgPlayer = playerManager.getSGPlayer(player.getSingle(e));
        if (mode == Changer.ChangeMode.SET) {
            sgPlayer.setBounty(bountyChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            sgPlayer.setBounty(sgPlayer.getBounty() + bountyChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            sgPlayer.setBounty(sgPlayer.getBounty() - bountyChange);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}
