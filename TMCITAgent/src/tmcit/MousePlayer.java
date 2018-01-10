package tmcit;

import org.aiwolf.sample.lib.AbstractRoleAssignPlayer;


public class MousePlayer extends AbstractRoleAssignPlayer {

	public MousePlayer() {
		setSeerPlayer(new MouseSeer());
		setVillagerPlayer(new MouseVillager());
		setWerewolfPlayer(new MouseWerewolf());
		setPossessedPlayer(new MousePossessed());
	}

	public String getName() {
		return "usami";
	}

}
