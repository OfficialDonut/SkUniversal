package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import e.Game;
import e.SGPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Teammate")
@Description("Returns the teammate of a player.")
@Examples({"send \"Your teammate is %survival games teammate of player%\"!"})
public class ExprTeammate extends SimpleExpression<Player> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player= (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the survival games teammate of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (player.getSingle(e) != null) {
            if (Game.getPlayerManager().getSGPlayer(player.getSingle(e)).getTeamMate().getPlayer().getPlayer() == null) {
                return null;
            }
            return new Player[]{Game.getPlayerManager().getSGPlayer(player.getSingle(e)).getTeamMate().getPlayer().getPlayer()};
        }else{
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        SGPlayer newTeammate = Game.getPlayerManager().getSGPlayer((OfflinePlayer)delta[0]);
        if (mode == Changer.ChangeMode.SET) {
            Game.getPlayerManager().getSGPlayer(player.getSingle(e)).setTeamMate(newTeammate);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Player.class);
        }
        return null;
    }
}
