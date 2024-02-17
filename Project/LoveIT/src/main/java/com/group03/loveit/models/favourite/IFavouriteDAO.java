package com.group03.loveit.models.favourite;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface IFavouriteDAO {
    public FavouriteDTO getFavouriteById(long id, long userId);
    public CompletableFuture<List<FavouriteDTO>> getFavouritesByUser(long userId);
    public void insertFavourite(FavouriteDTO favourite);
    public void deleteFavourite(long postId, long userId);
}
