package fr.ronan.exercicespringjdbc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;


@SpringBootApplication
public class ExerciceSpringJdbcApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*@PostConstruct
    public void test() {
        System.out.println("It's good " + jdbcTemplate);

        // Requete simple
        Integer resultat = jdbcTemplate.queryForObject("select count(0) from client", Integer.class);
        Integer nlCliCmd = jdbcTemplate.queryForObject("select count(DISTINCT nom_client, prenom_client) from commande", Integer.class);
        System.out.println("J'ai un nombre de clients à : " + nlCliCmd + " qui on passé une commande");
        int res = jdbcTemplate.update("insert into  commande (date_commande, nom_client, prenom_client, numero)" +
                        " values(?, ?, ?, ?)",
                new Date(), "RH", "HHH", new Date().getTime());
        System.out.println("Résultat de l'ajout : " + res);

        // Requete avec Named Parameters
        String motif = "%o%";
        SqlParameterSource params = new MapSqlParameterSource().addValue("motif", motif);
        int nbClientsTrouves = namedParameterJdbcTemplate.queryForObject("select count(0) from client where nom like :motif or prenom like :motif", params, Integer.class);
        System.out.println("Nombre de clients trouvés : " + nbClientsTrouves);

        // Requete compter le nombre de commande d'un client
        Client client = new Client();
        client.setNom("henry");
        client.setPrenom("ronan");
        var source = new BeanPropertySqlParameterSource(client);
        int nbCmdCli = namedParameterJdbcTemplate.queryForObject("select count(0) from commande where nom_client = :nom and prenom_client = :prenom", source, Integer.class);
        System.out.println("Notre client a passé " + nbCmdCli + " commande(s)");

        // Lister les clients
        RowMapper<Client> mapper = (ResultSet rs, int pos) -> {
            Client client0 = new Client();
            client0.setNom(rs.getString(1));
            client0.setPrenom(rs.getString(2));
            return client0;
        };
        Client resultatC = jdbcTemplate.queryForObject("select nom, prenom from client where nom ='ronan'", mapper);
        System.out.println(resultatC);
    }*/

    @PostConstruct
    public void test() {
        System.out.println("C'est OK " + jdbcTemplate);
        Integer resultat = jdbcTemplate.queryForObject(
                "select count(0) from client",
                Integer.class
        );
        System.out.println("J'ai un nombre de clients à : "
                + resultat);
        // nombre de clients qui ont passé au moins une commande
        Integer nbCliCmd = jdbcTemplate.queryForObject(
                "select count(distinct nom_client, prenom_client)" +
                        " from commande",
                Integer.class
        );
        System.out.println("nombre de vrais clients : " + nbCliCmd);

        // TODO : ajouter une commande à un client sans commande
        int res = jdbcTemplate.update("insert into commande (" +
                        "date_commande, nom_client, prenom_client, numero)" +
                        " values(?, ?, ?, ?)",
                new Date(), "Bolt", "Hussein", new Date().getTime());
        System.out.println("Résultat de l'ajout : " + res);

        String motif = "%ar%";
        Map<String, Object> params = Map.of("motif", motif);
        // var params = new MapSqlParameterSource();
        // params.addValue("motif", motif);
        int nbClientsTrouves = namedParameterJdbcTemplate.queryForObject(
                "select count(0) from client where nom like :motif" +
                        " or prenom like :motif",
                params,
                Integer.class
        );
        System.out.println("Nombre de clients trouvés : " + nbClientsTrouves);

        // Compter le nombre de commandes d'un client
        Client client = new Client();
        client.setNom("Richard");
        client.setPrenom("Second");

        var source =
                new BeanPropertySqlParameterSource(client);
        int nbCmdCli = namedParameterJdbcTemplate
                .queryForObject(
                        "select count(0) from commande " +
                                " where nom_client = :nom and " +
                                " prenom_client = :prenom ",
                        source,
                        Integer.class
                );
        System.out.println("Notre client a passé " +
                nbCliCmd +
                " commande(s)");

        // Lister les clients
        RowMapper<Client> mapper = (ResultSet rs, int pos) -> {
            Client client0 = new Client();
            client0.setNom(rs.getString(1));
            client0.setPrenom(rs.getString(2));

			/*int nbMax = rs.getMetaData().getColumnCount();
			for (int i=1; i<=nbMax; i++) {
				System.out.println(
						rs.getMetaData().getColumnName(i)
				);
			}*/

            return client0;
        };
        Client resultatC = jdbcTemplate.queryForObject(
                "select nom, prenom from client " +
                        "where nom = 'ronan'", mapper);
        System.out.println(resultatC);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExerciceSpringJdbcApplication.class, args);
    }
}
