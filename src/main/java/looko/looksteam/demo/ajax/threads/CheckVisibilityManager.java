package looko.looksteam.demo.ajax.threads;

import looko.looksteam.demo.api.CheckVisibilityState;

import java.util.concurrent.Callable;

public class CheckVisibilityManager implements Callable<Integer> {

    private String steamid;

    public CheckVisibilityManager(String steamid){
        this.steamid = steamid;
    }

    @Override
    public Integer call(){

        return new CheckVisibilityState().check(steamid).getCommunityvisibilitystate();
    }
}
