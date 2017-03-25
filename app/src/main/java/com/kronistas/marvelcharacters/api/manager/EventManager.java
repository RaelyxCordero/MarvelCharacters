package com.kronistas.marvelcharacters.api.manager;



import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.request.CharacterRequest;
import com.kronistas.marvelcharacters.api.request.ComicRequest;
import com.kronistas.marvelcharacters.api.request.CreatorRequest;
import com.kronistas.marvelcharacters.api.request.EventRequest;
import com.kronistas.marvelcharacters.api.request.SeriesRequest;
import com.kronistas.marvelcharacters.api.request.StoryRequest;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.service.Events;
import com.kronistas.marvelcharacters.api.service.Series;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.api.vo.Creator;
import com.kronistas.marvelcharacters.api.vo.Event;
import com.kronistas.marvelcharacters.api.vo.Story;

import retrofit.Callback;

/**
 * Manager that handles retrieval event information and requests related to a specific event id.
 *
 * Created by Trey Robinson on 2/22/14.
 */
public class EventManager extends BaseManager{

    private Events events;

    public EventManager() {
        events = MarvelApi.getInstance().getRestAdapter().create(Events.class);
    }

    /**
     * Retrieve all events matching the provided request parameters.
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void listCreators(EventRequest request, Callback<ServiceResponse<Event>> callback) {
        events.listEvents(request.getLimit()
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
                , parameterizeList(request.getCharacters())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve a event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param callback Handler called on request completion
     */
    public void getEventWithId(int eventId, CreatorRequest request, Callback<ServiceResponse<Event>> callback) {
        events.getEventWithId(eventId
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , callback);
    }

    /**
     * Retrieve all characters for an event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCharactersForEventId(int eventId, CharacterRequest request, Callback<ServiceResponse<Character>> callback){
        events.getCharactersForEventId(eventId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getComics())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all comics for an event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getComicsForEventId(int eventId, ComicRequest request, Callback<ServiceResponse<Comic>> callback) {
        events.getComicsForEventId(eventId
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
                , parameterizeList(request.getStories())
                , parameterizeList(request.getSharedAppearances())
                , parameterizeList(request.getCollaborators())
                , request.getOrderBy().getValue(), callback);
    }

    /**
     * Retrieve all creators for an event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCreatorsForEventId(int eventId, CreatorRequest request, Callback<ServiceResponse<Creator>> callback){
        events.getCreatorsForEventId(eventId
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
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all series for an event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getSeriesForEventId(int eventId, SeriesRequest request, Callback<ServiceResponse<Series>> callback) {
        events.getSeriesForEventId(eventId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getTitle()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getStories())
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getCreators())
                , request.getSeriesType().getValue()
                , request.getContains().getValue()
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all stories for an event with a specific ID.
     * @param eventId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getStoriesForEventId(int eventId, StoryRequest request, Callback<ServiceResponse<Story>> callback) {
        events.getStoriesForEventId(eventId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getModifiedSince()
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getOrderBy().getValue()
                , callback);
    }
}
