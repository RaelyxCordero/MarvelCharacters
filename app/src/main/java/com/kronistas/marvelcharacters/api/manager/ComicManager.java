package com.kronistas.marvelcharacters.api.manager;



import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.request.CharacterRequest;
import com.kronistas.marvelcharacters.api.request.ComicRequest;
import com.kronistas.marvelcharacters.api.request.CreatorRequest;
import com.kronistas.marvelcharacters.api.request.EventRequest;
import com.kronistas.marvelcharacters.api.request.RequestSignature;
import com.kronistas.marvelcharacters.api.request.StoryRequest;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.service.Comics;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.api.vo.Creator;
import com.kronistas.marvelcharacters.api.vo.Event;
import com.kronistas.marvelcharacters.api.vo.Story;

import retrofit.Callback;

/**
 * Manager that handles retrieval for comic information and requests related to a specific comic id.
 *
 * Created by Trey Robinson on 2/17/14.
 */
public class ComicManager extends  BaseManager{

    private Comics comics;

    public ComicManager() {
        comics = MarvelApi.getInstance().getRestAdapter().create(Comics.class);
    }

    /**
     * Retrieve all comics matching the provided request parameters.
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void listComics(ComicRequest request, Callback<ServiceResponse<Comic>> callback) {
        comics.listComics(request.getLimit()
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
     * Retrieve a comic with a specific ID.
     * @param comicId  Unique ID for the comic
     * @param callback Handler called on request completion
     */
    public void getComicWithId(int comicId, Callback<ServiceResponse<Comic>> callback){
        RequestSignature request = RequestSignature.create();
        comics.getComicWithId(comicId
                , String.valueOf(request.timeStamp)
                , request.publicKey
                , request.hashSignature
                , callback);
    }

    /**
     * Retrieve all characters for a comic with a specific ID.
     * @param comicId  Unique ID for the comic
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCharactersForComicId(int comicId, CharacterRequest request, Callback<ServiceResponse<Character>> callback){
        comics.getCharactersForComicId(comicId
                , request.getLimit()
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
     * Retrieve all creators for a comic with a specific ID.
     * @param comicId  Unique ID for the comic
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCreatorsForComicId(int comicId, CreatorRequest request, Callback<ServiceResponse<Creator>> callback){
        comics.getCreatorsForComicId(comicId
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
     * Retrieve events for a comic with a specific ID.
     * @param comicId  Unique ID for the comic
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getEventsForComicId(int comicId, EventRequest request, Callback<ServiceResponse<Event>> callback){
        comics.getEventsForComicId(comicId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve all stories for a comic with a specific ID.
     * @param comicId  Unique ID for the comic
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getStoriesForComicId(int comicId, StoryRequest request, Callback<ServiceResponse<Story>> callback){
        comics.getStoriesForComicId(comicId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getModifiedSince()
                , parameterizeList(request.getSeries())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getOrderBy().getValue()
                , callback);
    }
}
