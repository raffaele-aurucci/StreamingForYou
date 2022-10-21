drop schema if exists streaming;
create schema streaming;
use streaming;

create table utenteRegistrato
(
    CF char(16) primary key,
    nome varchar(20) not null,
    cognome varchar(20) not null,
    passwordUtente varchar(16) not null,
    speseSostenute double default 0
);

create table emailUtente
(
    email varchar(30) primary key,
    CFUtente char(16) references utenteRegistrato(CF) on delete cascade on update cascade
);

create table piattaforma
(
    nome varchar(20) primary key,
    indirizzoWeb varchar(30) not null,
    descrizione text,
    unique(indirizzoWeb)
);

create table accounts
(
    email varchar(30),
    nomePiattaforma varchar(20) not null,
    CFUtente varchar(16) not null,
    username varchar(20) default 'user' not null,
    passwordAccount varchar(16) default 'user' not null,
    primary key(email, nomePiattaforma),
    foreign key(nomePiattaforma) references piattaforma(nome) on delete cascade on update cascade,
    foreign key(CFUtente) references utenteRegistrato(CF) on delete cascade on update cascade
);

create table dispositivo
(
    indirizzoMAC varchar(12) primary key,
    nome varchar(20) not null,
    marca varchar(20) not null,
    modello varchar(20) not null
);

create table collegato
(
    indirizzoMAC varchar(12),
    emailAccount varchar(30),
    nomePiattaforma varchar(20),
    OTP varchar(5) not null,
    primary key(indirizzoMAC, emailAccount, nomePiattaforma),
    foreign key(indirizzoMAC) references dispositivo(indirizzoMAC) on delete cascade on update cascade,
    foreign key(emailAccount, nomePiattaforma) references accounts(email, nomePiattaforma) on delete cascade on update cascade
);

create table accesso
(
    codice integer auto_increment,
    CFUtente char(16) not null,
    dataAccesso timestamp not null,
    indirizzoIP varchar(30) not null,
    primary key(codice),
    foreign key(CFUtente) references utenteRegistrato(CF) on delete cascade on update cascade
);

create table pianoAbbonamento
(
    nome varchar(30),
    tipo varchar(20) not null,
    durata numeric not null,
    prezzo double not null,
    descrizione text not null,
    numDispositivi numeric not null,
    vantaggi text,
    limitazioni text,
    periodoDiProva numeric default 0,
    buonoRegalo double default 0,
    codiceSconto char(5),
    primary key(nome)
);

create table pagamento
(
    dataPagamento timestamp,
    nomeAbbonamento varchar(30),
    emailAccount varchar(30),
    nomePiattaforma varchar(20),
    numeroCarta varchar(16) not null,
    scadenzaCarta date not null,
    CVV character(3) not null,
    primary key(dataPagamento, nomeAbbonamento, emailAccount, nomePiattaforma),
    foreign key(nomeAbbonamento) references pianoAbbonamento(nome) on delete cascade on update cascade,
    foreign key(emailAccount, nomePiattaforma) references accounts(email, nomePiattaforma) on delete cascade on update cascade
);

Insert into utenteRegistrato (CF, nome, cognome, passwordUtente) values
                                                                     ("MRARSS01C10A294L","Mario","Rossi","1t£4as="),
                                                                     ("NCPL01454S6F912S","Nicola","Pagliara","1de457a");

insert into emailUtente values
                            ("rossimario10@hotmail.com", "MRARSS01C10A294L"),
                            ("nickpagliara15@outlook.com", "NCPL01454S6F912S");

Insert into piattaforma values
                            ("Amazon", "www.amazon.com","sport, film, serie TV, cartoon, musica"),
                            ("Netflix","www.netflix.com", "sport, film, serie TV, cartoon, musica"),
                            ("Dazn","www.dazn.it", "sport"),
                            ("Spotify","www.spotify.com", "musica"),
                            ("Mediaset Infinity","www.mediasetinfinity.it", "sport, film, serie TV, cartoon, musica"),
                            ("Disney Plus","www.disneyplus.com", "cartoon"),
                            ("Apple TV","www.appleTV.com", "film, serie TV, cartoon, musica");

Insert into dispositivo values
                            ("dhje78ehf8e9", "ipad", "apple", "air 2019"),
                            ("eu6ehddg7e89", "laptop2125", "asus", "tuf505dx"),
                            ("tr45wte6dsge", "laptop7895", "hp", "pavillion15"),
                            ("ijie874949jf", "fire tv stick", "amazon", "2020"),
                            ("or8978r4ur90", "iOS", "apple", "iphone13"),
                            ("lp8874r4ur99", "iOS", "apple", "iphone12");

insert into pianoAbbonamento values
                                 ("mini all inclusive", "base", 3, 2.99, "tutti i servizi inclusi per 3 giorni, riproduzione contemporanea su un massimo di 2 dispositivi", 2, null, "è possibile riprodurre contemporaneamente per un max di 24h, definizione audio/video low quality", null, null, "A345R"),
                                 ("classic all inclusive", "premium", 3, 4.99, "tutti i servizi inclusi per 3 giorni, riproduzione contemporanea su un massimo di 4 dispositivi", 4, "è possibile riprodurre tutti i servizi contemporaneamente senza limiti di tempo, definizione audio/video high quality", null, 1, 1.99, null),
                                 ("super mini inclusive", "base", 7, 5.99, "tutti i servizi inclusi per 7 giorni, riproduzione contemporanea su un massimo di 3 dispositivi", 3, null, "è possibile riprodurre contemporaneamente per un max di 72h, definizione audio/video mid quality", null, null, "BW21U"),
                                 ("super classic inclusive", "premium", 7, 7.99, "tutti i servizi inclusi per 7 giorni, riproduzione contemporanea su un massimo di 4 dispositivi", 4, "è possibile riprodurre tutti i servizi contemporaneamente senza limiti di tempo, definizione audio/video high quality", null, 2, 3.99, null),
                                 ("mini inclusive", "base", 1, 0.99, "tutti i servizi inclusi per 1 giorno, riproduzione contemporanea consentita su un massimo di 1 dispositivo", 1, null, "non è possibile riprodurre contemporaneamente su più dispositivi, definizione audio/video low quality", null, null, "WQ178"),
                                 ("maxi inclusive", "premium", 1, 1.99, "tutti i servizi inclusi per 1 giorno, riproduzione contemporanea consentita su un massimo di 2 dispositivi", 2, "non è possibile riprodurre contemporaneamente su più dispositivi, definizione audio/video mid quality", null, 0, 0, "IOP78"),
                                 ("ultra inclusive", "premium", 15, 8.99, "tutti i servizi inclusi per 15 giorni, riproduzione contemporanea consentita su un massimo di 6 dispositivi", 6, "nessuna limitazione, definizione audio/video ultra quality", null, 7, 4.99, null);

insert into accounts values
                         ("rossimario10@hotmail.com", "Netflix", "MRARSS01C10A294L", "rossi2", "ultraIstinto2"),
                         ("nickpagliara15@outlook.com", "Netflix", "NCPL01454S6F912S", "nick89", "grande9"),
                         ("rossimario10@hotmail.com", "Amazon", "MRARSS01C10A294L", "mario101", "password"),
                         ("nickpagliara15@outlook.com", "Amazon", "NCPL01454S6F912S", "rossi2", "grandeSaw9"),
                         ("nickpagliara15@outlook.com", "Spotify", "NCPL01454S6F912S", "nick89", "ciccioGamer4Ever"),
                         ("rossimario10@hotmail.com", "Spotify", "MRARSS01C10A294L", "rossi2", "ultraIstinto29"),
                         ("nickpagliara15@outlook.com", "Mediaset Infinity", "NCPL01454S6F912S", "nick89", "grandeSaw9"),
                         ("rossimario10@hotmail.com", "Dazn", "MRARSS01C10A294L", "rossi2", "ultraIstinto29"),
                         ("rossimario10@hotmail.com", "Mediaset Infinity", "MRARSS01C10A294L", "rossi2", "ultraIstinto29"),
                         ("rossimario10@hotmail.com", "Disney Plus", "MRARSS01C10A294L", "rossi29", "ultraIstinto29"),
                         ("rossimario10@hotmail.com", "Apple TV", "MRARSS01C10A294L", "rossi2", "ultraIstinto29");

insert into collegato values
                          ("dhje78ehf8e9", "rossimario10@hotmail.com", "Netflix", "12345"),
                          ("tr45wte6dsge", "rossimario10@hotmail.com", "Amazon", "54321"),
                          ("ijie874949jf", "nickpagliara15@outlook.com", "Netflix", "89789"),
                          ("or8978r4ur90", "rossimario10@hotmail.com", "Spotify", "56478"),
                          ("eu6ehddg7e89", "rossimario10@hotmail.com", "Amazon", "47895"),
                          ("lp8874r4ur99","nickpagliara15@outlook.com", "Spotify", "78459");

insert into accesso (CFUtente, dataAccesso, indirizzoIP) values
                                                             ("MRARSS01C10A294L", current_timestamp, "192.168.125.111"),
                                                             ("NCPL01454S6F912S", current_timestamp, "192.168.135.122"),
                                                             ("NCPL01454S6F912S", current_timestamp, "192.168.135.122"),
                                                             ("MRARSS01C10A294L", current_timestamp, "192.168.155.111"),
                                                             ("MRARSS01C10A294L", current_timestamp, "192.168.125.111"),
                                                             ("NCPL01454S6F912S", current_timestamp, "192.168.175.115");

insert into pagamento values
                          ('2022-01-01 09:00:00', "classic all inclusive", "rossimario10@hotmail.com", "Netflix", "123456789012", '2023-12-12', "123"),
                          ('2021-11-11 02:00:00', "classic all inclusive", "rossimario10@hotmail.com", "Netflix", "123456789012", '2023-12-12', "123"),
                          ('2021-12-11 02:00:00', "mini all inclusive", "rossimario10@hotmail.com", "Dazn", "123456789012", '2023-12-12', "123"),
                          ('2021-12-01 19:00:00', "ultra inclusive", "rossimario10@hotmail.com", "Amazon", "123456789012", '2023-12-12', "123"),
                          ('2021-06-01 19:30:00', "super classic inclusive", "nickpagliara15@outlook.com", "Netflix", "457894564586", '2024-06-12', "133"),
                          ('2021-01-01 18:00:00', "super mini inclusive", "nickpagliara15@outlook.com", "Spotify", "457894564586", '2024-06-12', "133"),
                          ('2020-11-01 20:00:00', "super classic inclusive", "rossimario10@hotmail.com", "Amazon", "7845787865425", '2026-12-08', "423"),
                          ('2021-12-21 21:00:00', "super mini inclusive", "rossimario10@hotmail.com", "Amazon", "457894564586", '2026-12-08', "423"),
                          ('2021-01-11 22:00:00', "maxi inclusive", "nickpagliara15@outlook.com", "Spotify", "789456123963", '2024-06-02', "153"),
                          ('2021-12-21 23:00:00', "mini inclusive", "nickpagliara15@outlook.com", "Spotify", "457894564586", '2024-06-02', "153");

-- Aggiornamento attributi derivabili
update utenteRegistrato set speseSostenute = (select sum(p2.prezzo)
                                              from (accounts a join Pagamento p on a.email = p.emailAccount and p.nomePiattaforma = a.nomePiattaforma) join pianoAbbonamento p2 on p2.nome = p.nomeAbbonamento
                                              where a.CFUtente = "MRARSS01C10A294L")
where utenteRegistrato.CF = "MRARSS01C10A294L";

update utenteRegistrato set speseSostenute = (select sum(p2.prezzo)
                                              from (accounts a join Pagamento p on a.email = p.emailAccount and p.nomePiattaforma = a.nomePiattaforma) join pianoAbbonamento p2 on p2.nome = p.nomeAbbonamento
                                              where a.CFUtente = "NCPL01454S6F912S")
where utenteRegistrato.CF = "NCPL01454S6F912S";