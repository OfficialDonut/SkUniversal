package us._donut_.skuniversal;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import us._donut_.skuniversal.advancedban.AdvancedBanRegister;
import us._donut_.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesRegister;
import us._donut_.skuniversal.autorank.AutorankRegister;
import us._donut_.skuniversal.bedwars.BedwarsRegister;
import us._donut_.skuniversal.bitcoin.BitcoinRegister;
import us._donut_.skuniversal.cannons.CannonsRegister;
import us._donut_.skuniversal.clearlagg.ClearLagRegister;
import us._donut_.skuniversal.combatlog.CombatLogRegister;
import us._donut_.skuniversal.griefprevention.GriefPreventionRegister;
import us._donut_.skuniversal.lockette.LocketteRegister;
import us._donut_.skuniversal.lockettepro.LocketteProRegister;
import us._donut_.skuniversal.luckperms.LuckPermsRegister;
import us._donut_.skuniversal.lwc.LWCRegister;
import us._donut_.skuniversal.minepacks.MinePacksRegister;
import us._donut_.skuniversal.parties.PartiesRegister;
import us._donut_.skuniversal.playerpoints.PlayerPointsRegister;
import us._donut_.skuniversal.plotsquared.PlotSquaredRegister;
import us._donut_.skuniversal.prisonmines.PrisonMinesRegister;
import us._donut_.skuniversal.pvplevels.PvPLevelsRegister;
import us._donut_.skuniversal.shopchest.ShopChestRegister;
import us._donut_.skuniversal.shopkeepers.ShopkeepersRegister;
import us._donut_.skuniversal.skywars_cookloco.SkywarsCookLocoRegister;
import us._donut_.skuniversal.skywars_daboross.SkyWarsDaborossRegister;
import us._donut_.skuniversal.slimefun.SlimefunRegister;

import java.util.ArrayList;
import java.util.List;

public class SkUniversal extends JavaPlugin {

    private List<String> hookedPlugins = new ArrayList<>();
    private Boolean serverHasPlugin(String pluginName) { return Bukkit.getPluginManager().getPlugin(pluginName) != null; }

    @Override
    public void onEnable() {
        Skript.registerAddon(this);
        if (serverHasPlugin("AdvancedBan")) { new AdvancedBanRegister(); hookedPlugins.add("AdvancedBan"); }
        if (serverHasPlugin("AdvancedSurvivalGames")) { new AdvancedSurvivalGamesRegister(); hookedPlugins.add("AdvancedSurvivalGames"); }
        if (serverHasPlugin("Autorank")) { new AutorankRegister(); hookedPlugins.add("Autorank"); }
        if (serverHasPlugin("Bedwars")) { new BedwarsRegister(); hookedPlugins.add("Bedwars"); }
        if (serverHasPlugin("Bitcoin")) { new BitcoinRegister(); hookedPlugins.add("Bitcoin"); }
        if (serverHasPlugin("Cannons")) { new CannonsRegister(); hookedPlugins.add("Cannons"); }
        if (serverHasPlugin("ClearLag")) { new ClearLagRegister(); hookedPlugins.add("ClearLag"); }
        if (serverHasPlugin("CombatLog")) { new CombatLogRegister(); hookedPlugins.add("CombatLog"); }
        if (serverHasPlugin("GriefPrevention")) { new GriefPreventionRegister(); hookedPlugins.add("GriefPrevention"); }
        if (serverHasPlugin("Lockette")) { new LocketteRegister(); hookedPlugins.add("Lockette"); }
        if (serverHasPlugin("LockettePro")) { new LocketteProRegister(); hookedPlugins.add("LockettePro"); }
        if (serverHasPlugin("LuckPerms")) { new LuckPermsRegister(); hookedPlugins.add("LuckPerms"); }
        if (serverHasPlugin("LWC")) { new LWCRegister(); hookedPlugins.add("LWC"); }
        if (serverHasPlugin("MinePacks")) { new MinePacksRegister(); hookedPlugins.add("Minepacks"); }
        if (serverHasPlugin("Parties")) { new PartiesRegister(); hookedPlugins.add("Parties"); }
        if (serverHasPlugin("PlayerPoints")) { new PlayerPointsRegister(); hookedPlugins.add("PlayerPoints"); }
        if (serverHasPlugin("PlotSquared")) {new PlotSquaredRegister(); hookedPlugins.add("PlotSquared"); }
        if (serverHasPlugin("PrisonMines")) { new PrisonMinesRegister(); hookedPlugins.add("PrisonMines"); }
        if (serverHasPlugin("PvPLevels")) { new PvPLevelsRegister(); hookedPlugins.add("PvPLevels"); }
        if (serverHasPlugin("Shopkeepers")) { new ShopkeepersRegister(); hookedPlugins.add("Shopkeepers"); }
        if (serverHasPlugin("ShopChest")) { new ShopChestRegister(); hookedPlugins.add("ShopChest"); }
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("CookLoco")) { new SkywarsCookLocoRegister(); hookedPlugins.add("SkyWars (CookLoco)"); }
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("Dabo Ross")) { new SkyWarsDaborossRegister(); hookedPlugins.add("SkyWars (Daboross)"); }
        if (serverHasPlugin("Slimefun")) { new SlimefunRegister(); hookedPlugins.add("Slimefun"); }
        getLogger().info(hookedPlugins.isEmpty() ? "Did not find any plugins to hook into." : "Hooked Plugins: " + hookedPlugins);
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() { getLogger().info("Disabled!"); }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("skuniversal")) {
            sender.sendMessage("");
            sender.sendMessage("§5§l§nSkUniversal v" + getDescription().getVersion());
            sender.sendMessage("");
            sender.sendMessage("§6Hooked plugins:");
            sender.sendMessage(hookedPlugins.isEmpty() ? "§eNone" : "§e" + hookedPlugins);
            sender.sendMessage("");
            sender.sendMessage("§6Plugin page:");
            sender.sendMessage("§ehttps://www.spigotmc.org/resources/skuniversal.45392/");
            sender.sendMessage("");
            sender.sendMessage("§6Documentation:");
            sender.sendMessage("§ehttps://github.com/OfficialDonut/SkUniversal/wiki");
            sender.sendMessage("");
            sender.sendMessage("§6Discord channel:");
            sender.sendMessage("§ehttps://discord.gg/UBGQhn8");
            return true;
        }
        return false;
    }
}