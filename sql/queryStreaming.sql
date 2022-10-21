use streaming;

-- 1) ELENCA TUTTI I PAGAMENTI AI PIANI D'ABBONAMENTO FATTI NEL 2021 NEL MESE DI DICEMBRE O GENNAIO ORDINATI PER DATA DI PAGAMENTO (numeroCarta, dataPagamento, nomeAbbonamento)
select p.numeroCarta, p.dataPagamento, p.nomeAbbonamento
from Pagamento p
where ((p.dataPagamento>='2021-12-01 00:00:00' and p.dataPagamento<='2021-12-31 23:59:59') or (p.dataPagamento>='2021-01-01 00:00:00' and p.dataPagamento<='2021-01-31 23:59:59'))
order by p.dataPagamento;

-- 2) ELENCA TUTTI I PAGAMENTI AI PIANI D'ABBONAMENTO PREMIUM FATTI NEL 2021 NEL MESE DI DICEMBRE O GENNAIO ORDINATI PER DATA DI PAGAMENTO (numeroCarta, dataPagamento, nomeAbbonamento)
select p.numeroCarta, p.dataPagamento, p.nomeAbbonamento
from Pagamento p join pianoAbbonamento a on p.nomeAbbonamento = a.nome
where a.tipo = 'premium' and ((p.dataPagamento>='2021-12-01 00:00:00' and p.dataPagamento<='2021-12-31 23:59:59') or (p.dataPagamento>='2021-01-01 00:00:00' and p.dataPagamento<='2021-01-31 23:59:59'))
order by p.dataPagamento;

-- 3) ELENCA TUTTI GLI UTENTI CHE HANNO CREATO UN ACCOUNT NETFLIX O SPOTIFY ORDINANODLI PER CODICE FISCALE (CF, nome, cognome)*/
select distinct u.CF, u.nome, u.cognome
from utenteRegistrato u join accounts a on u.CF = a.CFUtente
where a.nomePiattaforma = 'Netflix' or a.nomePiattaforma = 'Spotify'
order by u.CF;

-- 4) SOMMA TOTALE DELLE SPESE SOSTENUTE DA OGNI UTENTE REGISTRATO ALL' E-COMMERCE (totaleSpese)
select sum(speseSostenute) as totaleSpese
from utenteRegistrato;

-- 5) PER OGNI PIATTAFORMA CONOSCERE IL TOTALE DI ABBONAMENTI PREMIUM VENDUTI (nomePiattaforma, totaleAbbonamenti)
select p1.nomePiattaforma, count(*) as totaleAbbonamenti
from pagamento p1 join pianoAbbonamento p2 on p1.nomeAbbonamento = p2.nome
where p2.tipo = 'premium'
group by p1.nomePiattaforma;

-- 6) PER OGNI ABBONAMENTO PREMIUM CONOSCERE IL TOTALE GUADAGNATO DALL'E-COMMERCE (nomeAbbonamento, tipoAbbonamento, totaleGuadagnato)
select p2.nome, p2.tipo, sum(p2.prezzo) as totaleGuadagnato
from pagamento p1 join pianoAbbonamento p2 on p1.nomeAbbonamento = p2.nome
where p2.tipo = 'premium'
group by p2.nome, p2.tipo;

-- 7) VISUALIZZARE GLI ABBONAMENTI CHE HANNO PERMESSO ALL'E-COMMERCE DI GUADAGNARE PIU' DI 10 EURO (nomeAbbonamento, tipoAbbonamento, totaleGuadagnato)
select p2.nome, p2.tipo, sum(p2.prezzo) as totaleGuadagnato
from pagamento p1 join pianoAbbonamento p2 on p1.nomeAbbonamento = p2.nome
group by p2.nome, p2.tipo
having totaleGuadagnato > 10;

-- 8) VISUALIZZARE L'ABBONAMENTO CHE HA PERMESSO DI GUADAGNARE DI PIU' ALL'E-COMMERCE (nomeAbbonamento, tipoAbbonamento, totaleGuadagnato)
create view Guadagni as
select p2.nome, p2.tipo, sum(p2.prezzo) as totaleGuadagnato
from pagamento p1 join pianoAbbonamento p2 on p1.nomeAbbonamento = p2.nome
group by p2.nome, p2.tipo;

select *
from Guadagni
where totaleGuadagnato = (select max(totaleGuadagnato)
						  from Guadagni);
                          
-- 9) ELENCA TUTTI GLI UTENTI CHE HANNO CREATO UN ACCOUNT NETFLIX E SPOTIFY (CF, nome, cognome)
select distinct u.CF, u.nome, u.cognome
from utenteRegistrato u join accounts a on u.CF = a.CFUtente
where a.nomePiattaforma = 'Netflix' and u.CF in (select distinct u2.CF
												 from utenteRegistrato u2 join accounts a2 on u2.CF = a2.CFUtente
												 where a2.nomePiattaforma = 'Spotify');
 
 -- 10) ELENCA TUTTI GLI UTENTI CHE HANNO CREATO UN ACCOUNT NETFLIX MA NON DAZN (CF, nome, cognome)
select distinct u.CF, u.nome, u.cognome
from utenteRegistrato u join accounts a on u.CF = a.CFUtente
where a.nomePiattaforma = 'Netflix' and u.CF not in (select distinct u2.CF
													 from utenteRegistrato u2 join accounts a2 on u2.CF = a2.CFUtente
													 where a2.nomePiattaforma = 'Dazn');
                                                    
-- 11) UTENTI CHE HANNO CREATO SOLO ACCOUNT SPOTIFY (CF, cognome)
select distinct u.CF, u.cognome
from utenteRegistrato u join accounts a on a.CFUtente = u.CF
where u.CF not in (select distinct u2.CF
				   from utenteRegistrato u2 join accounts a2 on a2.CFUtente = u2.CF
				   where a2.nomePiattaforma != 'Spotify');
                   
-- 12) VISUALIZZARE GLI UTENTI CHE HANNO CREATO ALMENO UN ACCOUNT PER OGNI PIATTAFORMA A CUI L'E-COMMERCE È AFFILIATO (CF, cognome, nome)
 select u.CF, u.nome, u.cognome
 from utenteRegistrato u
 where not exists (select *
				   from piattaforma p
                   where not exists (select *
									 from accounts a
                                     where a.nomePiattaforma = p.nome && a.CFUtente = u.CF));