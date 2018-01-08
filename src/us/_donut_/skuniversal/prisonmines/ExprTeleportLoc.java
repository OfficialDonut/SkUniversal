package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PrisonMines - Teleport Location")
@Description("Returns the teleport location of a mine.")
@Examples({"send \"%the teleport location of the mine at player%\""})
public class ExprTeleportLoc extends SimpleExpression<Location> {

    MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "teleport location of mine named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (name.getSingle(e) != null) {
            return new Location[]{prisonMines.getByName(name.getSingle(e)).getTeleportLocation()};
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Location newLoc = (Location) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            prisonMines.getByName(name.getSingle(e)).setTeleportLocation(newLoc);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Location.class);
        }
        return null;
    }
}
