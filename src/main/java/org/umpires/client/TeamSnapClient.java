package org.umpires.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.umpires.client.model.Collection;
import org.umpires.client.model.TeamSnapResponse;
import org.umpires.client.model.dto.TeamSnapUmpire;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamSnapClient {
    //Need to authorize
    //need to get token
        //Need to set as parameters client_id, client_secret, redirect_uri, code and grant_type
            //get code from authorize
        //Grab access_token and token_type
    //need to get list of umpires
        //Use token_type + " " + access_token in Authorization Header
        //Need to get user id
        //Need to get team id using user id (filter that list with actual team name from Team Snap)
            //maybe have a team snap config table that will store the ids?
        //Call team members using team id
            //collection.items.data[]

    public List<TeamSnapUmpire> getUmpires() {
        return getTeamMembers();
    }

    private List<TeamSnapUmpire> getTeamMembers() {
        //hard coding ids for right now
        String teamId = "8605504";
        String url = "https://api.teamsnap.com/v3/members/search?team_id=";
        String token = "Bearer 3aq4Rm59AbdCcGqop_euI1gUfyHnsu0FcjJ8hC5ve7k";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<TeamSnapResponse> response = restTemplate.exchange(url + teamId, HttpMethod.GET, httpEntity, TeamSnapResponse.class);
            List<TeamSnapUmpire> umpires = new ArrayList<>();
            response.getBody().getCollection().getItems().forEach(items -> {
                if (!(Boolean)items.getData().stream().filter(data -> data.getName().equals("is_manager")).collect(Collectors.toList()).get(0).getValue()) {
                    TeamSnapUmpire umpire = new TeamSnapUmpire();
                    umpire.setFirstName((String) items.getData().stream().filter(data -> data.getName().equals("first_name")).collect(Collectors.toList()).get(0).getValue());
                    umpire.setLastName((String) items.getData().stream().filter(data -> data.getName().equals("last_name")).collect(Collectors.toList()).get(0).getValue());

                    umpires.add(umpire);
                }
            });

            return umpires;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
