package net.samagames.hub.gui.main;

import net.samagames.hub.Hub;
import net.samagames.hub.games.AbstractGame;
import net.samagames.hub.gui.AbstractGui;
import net.samagames.hub.gui.staff.GuiSelectZone;
import net.samagames.permissionsbukkit.PermissionsBukkit;
import net.samagames.tools.BungeeUtils;
import net.samagames.tools.events.EventUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GuiMain extends AbstractGui
{
    @Override
    public void display(Player player)
    {
        boolean staffFlag = PermissionsBukkit.hasPermission(player, "beta.staff");

        this.inventory = Bukkit.createInventory(null, 27, "Menu Principal");

        this.setSlotData(ChatColor.GOLD + "Zone BETA - " + ChatColor.GREEN + "VIP", Material.DIAMOND, 0, this.getLores(new String[] { "Testez les jeux avant tout le monde !" }, false, true), "beta_vip");
        this.setSlotData(ChatColor.GOLD + "Spawn", Material.BED, 9, this.getLores(null, false, true), "spawn");
        this.setSlotData(ChatColor.GOLD + "Jump du Ciel", Material.PACKED_ICE, 17, this.getLores(null, false, true), "jump");
        this.setSlotData(ChatColor.GOLD + "Informations", Material.EMPTY_MAP, 18, this.getInformationLores(), "none");
        this.setSlotData(ChatColor.GOLD + "Changer de hub", Material.ENDER_CHEST, 26, this.getLores(null, true, false), "switch_hub");

        if(!EventUtils.isCurrentlyEvent())
            this.setSlotData(ChatColor.GOLD + "Évenement", Material.IRON_FENCE, 8, this.getLores(new String[] { "Aucun évenement en cours !" }, false, false), "event");
        else
            this.setSlotData(ChatColor.GOLD + "Évenement - " + EventUtils.getCurrentEvent().getName(), Material.CAKE, 8, this.getLores(new String[] { EventUtils.getCurrentEvent().getDescription() }, true, false), "event");

        for(String gameIdentifier : Hub.getInstance().getGameManager().getGames().keySet())
        {
            AbstractGame game = Hub.getInstance().getGameManager().getGameByIdentifier(gameIdentifier);

            if(game.getSlotInMainMenu() != -1)
                this.setSlotData(ChatColor.GOLD + game.getName(), game.getIcon(), game.getSlotInMainMenu(), this.getLores(game.getDescription(), false, true), "game_" + gameIdentifier);
        }

        player.openInventory(this.inventory);
    }

    @Override
    public void onClick(Player player, ItemStack stack, String action, ClickType clickType)
    {
        if(action.equals("beta_vip"))
        {
            if(PermissionsBukkit.hasPermission(player, "beta.staff"))
            {
                Hub.getInstance().getGuiManager().openGui(player, new GuiSelectZone());
                return;
            }

            player.teleport(Hub.getInstance().getGameManager().getGameByIdentifier("beta_vip").getLobbySpawn());
        }
        else if(action.equals("switch_hub"))
        {
            Hub.getInstance().getGuiManager().openGui(player, new GuiSwitchHub());
        }
        else if(action.equals("event"))
        {
            if(EventUtils.isCurrentlyEvent())
                BungeeUtils.sendPlayerToServer(player, EventUtils.getCurrentEvent().getServer());
        }
        else if(action.equals("spawn"))
        {
            player.teleport(Hub.getInstance().getPlayerManager().getLobbySpawn());
        }
        else if(action.equals("jump"))
        {
            player.teleport(Hub.getInstance().getJumpManager().getJumps().get(0).getSpawn());
        }
        else if(action.startsWith("game"))
        {
            String[] actions = action.split("_");
            AbstractGame game = Hub.getInstance().getGameManager().getGameByIdentifier(actions[1]);

            if(!game.isLocked())
                player.teleport(game.getLobbySpawn());
            else
                player.sendMessage(ChatColor.RED + "Ce jeu n'est pas disponible !");
        }
    }

    private String[] getLores(String[] description, boolean clickOpen, boolean clickTeleport)
    {
        ArrayList<String> lores = new ArrayList<>();
        String[] loresArray = new String[] {};

        if(description != null)
        {
            for (String string : description)
                lores.add(ChatColor.GRAY + string);

            if (clickOpen || clickTeleport)
                lores.add("");
        }

        if(clickOpen)
            lores.add(ChatColor.DARK_GRAY + "▶ Clique pour ouvrir le menu");

        if(clickTeleport)
            lores.add(ChatColor.DARK_GRAY + "▶ Clique pour être téléporté");

        return lores.toArray(loresArray);
    }

    private String[] getInformationLores()
    {
        return new String[] {
                ChatColor.DARK_GRAY + "Site internet : " + ChatColor.GRAY + "http://samagames.net",
                ChatColor.DARK_GRAY + "Forum : " + ChatColor.GRAY + "http://samagames.net/forum/",
                ChatColor.DARK_GRAY + "Boutique : " + ChatColor.GRAY + "http://samagames.net/boutique/",
                "",
                ChatColor.DARK_GRAY + "TeamSpeak : " + ChatColor.GRAY + "ts.samagames.net"
        };
    }
}
