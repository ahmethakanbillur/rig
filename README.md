# rig
Readingisgood Assessment

Spring Boot 2.7.3 ile geliştirme tercih edildi.
JWT entegrasyonu ile başlayıp daha sonrasında swagger entregrasyonu ile devam edildi.JWT daha rahat test edilebilmesi için sonrasında swagger entegrasyonu yapıldı.Swagger dökümasyonu yazıldı.
Swagger kurulumu sonrasında swagger-ui ekranında rest api'leri görüntüleme problemi yaşandı.
	Bu problemi çözmek için springfox-boot-starter 3.0 tercih dildi ve app.properties dosyasına spring.mvc.pathmatch.matching-strategy=ant_path_matcher eklemek yeterli oldu.
JWT whitelist eklendi ve end point entegrasyonlarına başlandı.
Mapstruct ile req/resp payload ayrımına gidilerek data izolasyonu sağlandı.
Non-Relational database yerine relational in-memory database(h2) kullanımı tercih edildi çünkü ayrıca bir mapper(mapstruct) ihtiyacı doğacaktı.
Data.sql dosyası resource altına eklendi.(insert script)
JPA spesifikasyonu tercih edildi.Müşteri bazlı sipariş sorgusuna pageable eklendi(Test için page=0 ve size=10 kullanabilirsiniz.).
Docker file ile containerized edildi.
