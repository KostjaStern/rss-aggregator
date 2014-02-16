
CREATE SEQUENCE auto_id_rss_feeds;

CREATE TABLE rss_feeds (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_feeds'),
    name    varchar(50),
    url     varchar(250),
	PRIMARY KEY (id)
);


CREATE SEQUENCE auto_id_rss_news;

CREATE TABLE rss_news (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_news'),
	feed_id integer,
    title   varchar(250),
    link    varchar(250),
	PRIMARY KEY (id)
);

