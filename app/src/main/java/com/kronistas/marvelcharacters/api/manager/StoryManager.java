package com.kronistas.marvelcharacters.api.manager;



import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.request.CharacterRequest;
import com.kronistas.marvelcharacters.api.request.ComicRequest;
import com.kronistas.marvelcharacters.api.request.CreatorRequest;
import com.kronistas.marvelcharacters.api.request.EventRequest;
import com.kronistas.marvelcharacters.api.request.SeriesRequest;
import com.kronistas.marvelcharacters.api.request.StoryRequest;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.service.Series;
import com.kronistas.marvelcharacters.api.service.Stories;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.api.vo.Creator;
import com.kronistas.marvelcharacters.api.vo.Event;
import com.kronistas.marvelcharacters.api.vo.Story;

import retrofit.Callback;

/**
 * Manager that handles retrieval of story information and requests related to a specific story id.
 *
 * Created by Trey Robinson on 2/23/14.
 */
public class StoryManager extends BaseManager {

    private Stories stories;

    public StoryManager(){
        stories = MarvelApi.getInstance().getRestAdapter().create(Stories.class);
    }

    /**
     * Retrieve stories matching the provided request parameters.
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void listStories(StoryRequest request, Callback<ServiceResponse<Story>> callback) {
        stories.listStories(request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getModifiedSince()
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param callback Handler called on request completion
     */
    public void getStoryWithId(int storyId, StoryRequest request, Callback<ServiceResponse<Story>> callback) {
        stories.getStoryWithId(storyId
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , callback);
    }

    /**
     * Retrieve characters for a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCharactersForStoryId(int storyId, CharacterRequest request, Callback<ServiceResponse<Character>> callback){
        stories.getCharactersForStoryId(storyId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve comics for a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getComicsForStoryId(int storyId, ComicRequest request, Callback<ServiceResponse<Comic>> callback) {
        stories.getComicsForStoryId(storyId
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
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getSharedAppearances())
                , parameterizeList(request.getCollaborators())
                , request.getOrderBy().getValue(), callback);
    }

    /**
     * Retrieve creators for a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCreatorsForSeriesId(int storyId, CreatorRequest request, Callback<ServiceResponse<Creator>> callback){
        stories.getCreatorsForStoryId(storyId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getFirstName()
                , request.getMiddleName()
                , request.getLastName()
                , request.getSuffix()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve events for a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getEventsForStoryId(int storyId, EventRequest request, Callback<ServiceResponse<Event>> callback) {
        stories.getEventsForStoryId(storyId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getSeries())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve series for a story with a specific ID.
     * @param storyId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getSeriesForStoryId(int storyId, SeriesRequest request, Callback<ServiceResponse<Series>> callback) {
        stories.getSeriesForStoryId(storyId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getTitle()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getSeriesType().getValue()
                , request.getContains().getValue()
                , request.getOrderBy().getValue()
                , callback);
    }
}