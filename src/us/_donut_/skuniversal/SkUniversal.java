package us._donut_.skuniversal;

import ch.njol.skript.Skript;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import us._donut_.skuniversal.advancedban.AdvancedBanRegister;
import us._donut_.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesRegister;
import us._donut_.skuniversal.autorank.AutorankRegister;
import us._donut_.skuniversal.bedwars.BedwarsRegister;
import us._donut_.skuniversal.clearlagg.ClearLagRegister;
import us._donut_.skuniversal.combatlog.CombatLogRegister;
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
    public static List<String> hookedPlugins = new ArrayList<>();

    @Override
    public void onEnable() {
        Skript.registerAddon(this);
        AdvancedBanRegister.registerAdvancedBan();
        AdvancedSurvivalGamesRegister.registerAdvancedSurvivalGames();
        AutorankRegister.registerAutorank();
        BedwarsRegister.registerBedwars();
        ClearLagRegister.registerClearLagg();
        CombatLogRegister.registerCombatLog();
        LocketteRegister.registerLockette();
        LocketteProRegister.registerLockettePro();
        LWCRegister.registerLWC();
        MinePacksRegister.registerMinePacks();
        PartiesRegister.registerParties();
        PlotSquaredRegister.registerPlotSquared();
        PrisonMinesRegister.registerPrisonMines();
        PvPLevelsRegister.registerPvPLevels();
        ShopkeepersRegister.registerShopekeepers();
        SkywarsCookLocoRegister.registerSkyWarsCookLoco();
        SkyWarsDaborossRegister.registerSkyWarsDaboross();
        if (hookedPlugins.isEmpty()) {
            getLogger().warning("Did not find any plugins to hook into.");
        } else {
            getLogger().info("Hooked Plugins: " + hookedPlugins);
        }
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
    }

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