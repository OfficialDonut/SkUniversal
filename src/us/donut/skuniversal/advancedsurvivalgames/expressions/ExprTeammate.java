package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import e.SGPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesHook.playerManager;

@Name("AdvancedSurvivalGames - Teammate")
@Description("Returns the teammate of a player.")
@Examples({"send \"Your teammate is %survival games teammate of player%\"!"})
public class ExprTeammate extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprTeammate.class, Player.class, ExpressionType.COMBINED,
                "[the] [[advanced] (survival games|sg)] teammate of %offlineplayer%",
                "%offlineplayer%'s [[advanced] (survival games|sg)] teammate");
    }

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player= (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the survival games teammate of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        SGPlayer teammate = playerManager.getSGPlayer(player.getSingle(e)).getTeamMate();
        return teammate == null ? null : new Player[]{teammate.getPlayer().getPlayer()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (player.getSingle(e) == null) return;
        SGPlayer newTeammate = playerManager.getSGPlayer((Player)delta[0]);
        if (mode == Changer.ChangeMode.SET) {
            playerManager.getSGPlayer(player.getSingle(e)).setTeamMate(newTeammate);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(Player.class) : null;
    }
}
