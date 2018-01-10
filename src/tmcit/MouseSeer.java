package tmcit;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.aiwolf.client.lib.ComingoutContentBuilder;
import org.aiwolf.client.lib.Content;
import org.aiwolf.client.lib.ContentBuilder;
import org.aiwolf.client.lib.DivinedResultContentBuilder;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Judge;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Species;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.sample.lib.AbstractSeer;

public class MouseSeer extends AbstractSeer {
	Agent me;
	GameInfo currentGameInfo;
	Deque<Judge> myDivinationQueue=new LinkedList<>();
	List<Agent> whiteList=new ArrayList<>();
	List<Agent> blackList=new ArrayList<>();
	List<Agent> grayList;
	boolean isCO=false;

	boolean isAlive(Agent agent) {
		return currentGameInfo.getAliveAgentList().contains(agent);
	}

	<T> T randomSelect(List<T> list) {
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get((int) (Math.random() * list.size()));
		}
	}

	@Override
	public void dayStart() {
		Judge divination = currentGameInfo.getDivineResult();
		if(divination!=null) {
			myDivinationQueue.offer(divination);
			Agent target=divination.getTarget();
			Species result=divination.getResult();

			grayList.remove(target);
			if(result==Species.HUMAN) {
				whiteList.add(target);
			} else {
				blackList.add(target);
			}
		}

	}

	@Override
	public Agent divine() {
		List<Agent> candidates=new ArrayList<>();
		for(Agent agent : grayList) {
			if(isAlive(agent)) {
				candidates.add(agent);
			}
		}

		if(candidates.isEmpty()) {
		return null;
		}

		return randomSelect(candidates);
	}


	@Override
	public void finish() {
	}

	@Override
	public String getName() {
		return "MouseSeer";
	}

	@Override
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		me=gameInfo.getAgent();
		grayList=new ArrayList<>(gameInfo.getAgentList());
		grayList.remove(me);
		whiteList.clear();
		blackList.clear();
		myDivinationQueue.clear();
	}

	public String talk() {
		if(!isCO) {
		if(!myDivinationQueue.isEmpty() &&
			myDivinationQueue.peek().getResult()==Species.WEREWOLF) {
			   isCO=true;
			   ContentBuilder builder = new ComingoutContentBuilder(me,Role.SEER);
			   return new Content(builder).getText();
		    }
		}
		else {
			if(!myDivinationQueue.isEmpty()) {
				Judge divination=myDivinationQueue.poll();
				ContentBuilder builder=new DivinedResultContentBuilder(divination.getTarget(), divination.getResult());
			    return new Content(builder).getText();
			}
		}

		return Content.OVER.getText();
	}

	@Override
	public void update(GameInfo gameInfo) {
		currentGameInfo=gameInfo;
	}

	@Override
	public Agent vote() {
		List<Agent> candidates=new ArrayList<>();

		for(Agent agent : blackList) {
			if(isAlive(agent)) {
				candidates.add(agent);
			}
		}

		if(candidates.isEmpty()) {
			for(Agent agent : grayList) {
				if(isAlive(agent)) {
					candidates.add(agent);
				}
			}
		}
		if(candidates.isEmpty()) {
			return null;
		}

		return randomSelect(candidates);
	}

}
