package com.group03.loveit.models.favourite;

/**
 * @author Nhat
 */
public interface IFavouriteDAO {
    public FavouriteDTO getFavouriteById(long id, long userId);
    public void insertFavourite(FavouriteDTO favourite);
    public void deleteFavourite(long postId, long userId);
}
