package ma.projet.restclient.api;

import ma.projet.restclient.entities.Compte;
import ma.projet.restclient.entities.CompteList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CompteService {
    // Tous les comptes au format JSON
    @GET("banque/comptes")
    @Headers("Accept: application/json")
    Call<List<Compte>> getAllCompteJson();

    // Tous les comptes au format XML (wrapper)
    @GET("banque/comptes")
    @Headers("Accept: application/xml")
    Call<CompteList> getAllCompteXml();

    // Obtenir un compte par ID
    @GET("banque/comptes/{id}")
    Call<Compte> getCompteById(@Path("id") Long id);

    // Créer un compte
    @POST("banque/comptes")
    Call<Compte> addCompte(@Body Compte compte);

    // Mettre à jour
    @PUT("banque/comptes/{id}")
    Call<Compte> updateCompte(@Path("id") Long id, @Body Compte compte);

    // Supprimer
    @DELETE("banque/comptes/{id}")
    Call<Void> deleteCompte(@Path("id") Long id);
}