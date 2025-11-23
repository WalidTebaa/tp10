package ma.projet.restclient.repository;

import ma.projet.restclient.api.CompteService;
import ma.projet.restclient.entities.Compte;
import ma.projet.restclient.entities.CompteList;
import ma.projet.restclient.config.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompteRepository {
    // client Retrofit qui appelle l'API
    private CompteService apiClient;
    // format attendu: "JSON" ou "XML"
    private String mode;

    // Constructeur: on fournit le type de convertisseur (JSON/XML)
    public CompteRepository(String converterType) {
        this.apiClient = RetrofitClient.getClient(converterType).create(CompteService.class);
        this.mode = converterType;
    }

    // Récupère tous les comptes; transmet le résultat via le callback fourni
    public void getAllCompte(Callback<List<Compte>> callback) {
        if ("JSON".equalsIgnoreCase(mode)) {
            Call<List<Compte>> call = apiClient.getAllCompteJson();
            call.enqueue(callback);
            return;
        }

        // Si on veut de l'XML, on récupère d'abord le wrapper CompteList
        Call<CompteList> xmlCall = apiClient.getAllCompteXml();
        xmlCall.enqueue(new Callback<CompteList>() {
            @Override
            public void onResponse(Call<CompteList> call, Response<CompteList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Compte> comptes = response.body().getComptes();
                    // On notifie le callback externe avec la liste extraite
                    callback.onResponse(null, Response.success(comptes));
                } else {
                    // Propager une réponse vide/erreur au callback externe
                    callback.onResponse(null, Response.success(null));
                }
            }

            @Override
            public void onFailure(Call<CompteList> call, Throwable t) {
                // Transmettre l'erreur au callback externe
                callback.onFailure(null, t);
            }
        });
    }

    // Récupérer un compte par identifiant
    public void getCompteById(Long id, Callback<Compte> callback) {
        Call<Compte> call = apiClient.getCompteById(id);
        call.enqueue(callback);
    }

    // Ajouter un nouveau compte
    public void addCompte(Compte compte, Callback<Compte> callback) {
        Call<Compte> call = apiClient.addCompte(compte);
        call.enqueue(callback);
    }

    // Mettre à jour un compte existant
    public void updateCompte(Long id, Compte compte, Callback<Compte> callback) {
        Call<Compte> call = apiClient.updateCompte(id, compte);
        call.enqueue(callback);
    }

    // Supprimer un compte
    public void deleteCompte(Long id, Callback<Void> callback) {
        Call<Void> call = apiClient.deleteCompte(id);
        call.enqueue(callback);
    }
}