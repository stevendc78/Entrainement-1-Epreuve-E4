package composants;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import outils.ListeUtilisateurDTO;
import outils.UtilisateurDTO;

@ManagedBean(name="controleur")
@RequestScoped
public class TableurGUI {

	private List<UtilisateurDTO> listeUtilisateurs = new ArrayList<>();

	@PostConstruct
	public void controler() {
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatauthentificateur:8080/tableur"));
		WebTarget ciblefinale = cible.path("listedto");
		ListeUtilisateurDTO dtoliste = ciblefinale.request(MediaType.APPLICATION_XML).get(ListeUtilisateurDTO.class);
		listeUtilisateurs = dtoliste.getListedto();
	}

	public List<UtilisateurDTO> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<UtilisateurDTO> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public TableurGUI() {
		super();
}

	
}
