package us._donut_.skuniversal;

import ch.njol.skript.Skript;
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
import us._donut_.skuniversal.lwc.LWCRegister;
import us._donut_.skuniversal.minepacks.MinePacksRegister;
import us._donut_.skuniversal.parties.PartiesRegister;
import us._donut_.skuniversal.plotsquared.PlotSquaredRegister;
import us._donut_.skuniversal.prisonmines.PrisonMinesRegister;
import us._donut_.skuniversal.pvplevels.PvPLevelsRegister;
import us._donut_.skuniversal.shopkeepers.ShopkeepersRegister;
import us._donut_.skuniversal.skywars_cookloco.SkywarsCookLocoRegister;
import us._donut_.skuniversal.skywars_daboross.SkyWarsDaborossRegister;

import java.util.ArrayList;
import java.util.List;

public class SkUniversal extends JavaPlugin {

    private List<String> hookedPlugins = new ArrayList<>();
    private Boolean serverHasPlugin(String pluginName) { return getServer().getPluginManager().getPlugin(pluginName) != null; }

    @Override
    public void onEnable() {
        Skript.registerAddon(this);
        if (serverHasPlugin("AdvancedBan")) { AdvancedBanRegister.registerAdvancedBan(); hookedPlugins.add("AdvancedBan"); }
        if (serverHasPlugin("AdvancedSurvivalGames")) { AdvancedSurvivalGamesRegister.registerAdvancedSurvivalGames(); hookedPlugins.add("AdvancedSurvivalGames"); }
        if (serverHasPlugin("Autorank")) { AutorankRegister.registerAutorank(); hookedPlugins.add("Autorank"); }
        if (serverHasPlugin("Bedwars")) { BedwarsRegister.registerBedwars(); hookedPlugins.add("Bedwars"); }
        if (serverHasPlugin("Bitcoin")) { BitcoinRegister.registerBitcoin(); hookedPlugins.add("Bitcoin"); }
        if (serverHasPlugin("Cannons")) { CannonsRegister.registerCannons(); hookedPlugins.add("Cannons"); }
        if (serverHasPlugin("ClearLag")) { ClearLagRegister.registerClearLagg(); hookedPlugins.add("ClearLag"); }
        if (serverHasPlugin("CombatLog")) { CombatLogRegister.registerCombatLog(); hookedPlugins.add("CombatLog"); }
        if (serverHasPlugin("GriefPrevention")) { GriefPreventionRegister.registerGriefPrevention(); hookedPlugins.add("GriefPrevention"); }
        if (serverHasPlugin("Lockette")) { LocketteRegister.registerLockette(); hookedPlugins.add("Lockette"); }
        if (serverHasPlugin("LockettePro")) { LocketteProRegister.registerLockettePro(); hookedPlugins.add("LockettePro"); }
        if (serverHasPlugin("LWC")) { LWCRegister.registerLWC(); hookedPlugins.add("LWC"); }
        if (serverHasPlugin("MinePacks")) { MinePacksRegister.registerMinePacks(); hookedPlugins.add("Minepacks"); }
        if (serverHasPlugin("Parties")) { PartiesRegister.registerParties(); hookedPlugins.add("Parties"); }
        if (serverHasPlugin("PlotSquared")) { PlotSquaredRegister.registerPlotSquared(); hookedPlugins.add("PlotSquared"); }
        if (serverHasPlugin("PrisonMines")) { PrisonMinesRegister.registerPrisonMines(); hookedPlugins.add("PrisonMines"); }
        if (serverHasPlugin("PvPLevels")) { PvPLevelsRegister.registerPvPLevels(); hookedPlugins.add("PvPLevels"); }
        if (serverHasPlugin("Shopkeepers")) {ShopkeepersRegister.registerShopekeepers(); hookedPlugins.add("Shopkeepers"); }
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("CookLoco")) { SkywarsCookLocoRegister.registerSkyWarsCookLoco(); hookedPlugins.add("SkyWars (CookLoco)"); }
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("Dabo Ross")) { SkyWarsDaborossRegister.registerSkyWarsDaboross(); hookedPlugins.add("SkyWars (Daboross)"); }

        if (hookedPlugins.isEmpty()) {
            getLogger().warning("Did not find any plugins to hook into.");
        } else {
            getLogger().info("Hooked Plugins: " + hookedPlugins);
        }
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
            if (hookedPlugins.isEmpty()) {
                sender.sendMessage("§eNone");
            } else {
                sender.sendMessage("§e" + hookedPlugins);
            }
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