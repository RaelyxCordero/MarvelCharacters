package com.kronistas.marvelcharacters.api.manager;



import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.request.CharacterRequest;
import com.kronistas.marvelcharacters.api.request.ComicRequest;
import com.kronistas.marvelcharacters.api.request.EventRequest;
import com.kronistas.marvelcharacters.api.request.RequestSignature;

import com.kronistas.marvelcharacters.api.request.SeriesRequest;
import com.kronistas.marvelcharacters.api.request.StoryRequest;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.service.Characters;
import com.kronistas.marvelcharacters.api.service.Series;
import com.kronistas.marvelcharacters.api.vo.Character;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.api.vo.Event;

import com.kronistas.marvelcharacters.api.vo.Story;

import retrofit.Callback;

/**
 * Manager that handles retrieval of character information and requests related to a specific character id.
 *
 * Created by Trey Robinson on 2/12/14.
 */
public class CharacterManager extends BaseManager {

    private Characters characters;

    public CharacterManager() {
        characters = MarvelApi.getInstance().getRestAdapter().create(Characters.class);
    }

    /**
     * Retrieve all characters matching the provided request parameters.
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void listCharacters(CharacterRequest request, Callback<ServiceResponse<Character>> callback) {
        characters.listCharacters(request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getStories())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve a character with a specific ID.
     * @param characterId  Unique ID for the character that will be returned by the service
     * @param callback Handler called on request completion
     */
    public void getCharacterWithId(int characterId, Callback<ServiceResponse<Character>> callback) {
        RequestSignature request = RequestSignature.create();
        characters.getCharacterWithId(characterId
                , String.valueOf(request.timeStamp)
                , request.publicKey
                , request.hashSignature
                , callback);
    }

    /**
     * Retrieve all comics for a specific character that match the provided request parameters.
     * @param characterId  Unique ID for the character
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getComicsForCharacterId(int characterId, ComicRequest request, Callback<ServiceResponse<Comic>> callback) {
        characters.getComicsForCharacterId(characterId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getFormat().getValue()
                , request.getFormatType().getValue()
                , request.isNoVariants()
                , request.getDateDescriptor().getValue()
                , request.getDateRange()
                , request.isHasDigitalIssue()
                , request.getModifiedSince()
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getStories())
                , parameterizeList(request.getSharedAppearances())
                , parameterizeList(request.getCollaborators())
                , request.getOrderBy().getValue(), callback);
    }


    /**
     * Retrieve all events for a specific character that match the provided request parameters.
     * @param characterId  Unique ID for the character
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getEventsForCharacterId(int characterId, EventRequest request, Callback<ServiceResponse<Event>> callback) {
        characters.getEventsForCharacterId(characterId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getComics())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all series for a specific character that match the provided request parameters.
     * @param characterId  Unique ID for the character
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getSeriesForCharacterId(int characterId, SeriesRequest request, Callback<ServiceResponse<Series>> callback) {
        characters.getSeriesForCharacterId(characterId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getTitle()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getStories())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getCreators())
                , request.getSeriesType().getValue()
                , request.getContains().getValue()
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all Stories for a specific character that match the provided request parameters.
     * @param characterId  Unique ID for the character
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getStoriesForCharacterId(int characterId, StoryRequest request, Callback<ServiceResponse<Story>> callback){
        characters.getStoriesForCharacterId(characterId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                ,request.getApiKey()
                , request.getHashSignature()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getCreators())
                , request.getOrderBy().getValue()
                , callback);
    }
}
