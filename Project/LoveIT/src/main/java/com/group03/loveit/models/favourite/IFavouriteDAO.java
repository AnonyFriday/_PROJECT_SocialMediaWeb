package com.group03.loveit.models.favourite;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface IFavouriteDAO {
    CompletableFuture<FavouriteDTO> getFavouriteById(long postId, long userId);
    CompletableFuture<List<FavouriteDTO>> getFavouritesByUser(long userId);
    CompletableFuture<List<FavouriteDTO>> getFavouritesByPost(long postId);
    CompletableFuture<Void> insertFavourite(FavouriteDTO favourite);
    CompletableFuture<Void> deleteFavourite(long postId, long userId);
}
