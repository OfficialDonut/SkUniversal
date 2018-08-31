package us._donut_.skuniversal.prisonmines.expressions;

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
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.prisonmines.PrisonMinesHook.*;

@Name("PrisonMines - Teleport Location")
@Description("Returns the teleport location of a mine.")
@Examples({"send \"%the teleport location of the mine at player%\""})
public class ExprTeleportLoc extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprTeleportLoc.class, Location.class, ExpressionType.COMBINED, "[the] (teleport|tp) loc[ation] of [the] [PrisonMines] mine [(named|with name)] %string%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "teleport location of mine named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Mine mine;
        if (name.getSingle(e) == null || (mine = mineAPI.getByName(name.getSingle(e))) == null) return null;
        return new Location[]{mine.getTeleportLocation()};
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Mine mine;
        if (name.getSingle(e) == null || (mine = mineAPI.getByName(name.getSingle(e))) == null) return;
        Location newLoc = (Location) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            mine.setTeleportLocation(newLoc);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(Location.class) : null;
    }
}
