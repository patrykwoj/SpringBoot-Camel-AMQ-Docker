package pl.patrykwoj.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
	@Bean
	public PropertyMap<pl.patrykwoj.entity.Message, pl.patrykwoj.dto.Message> messageEntityToDto() {
		return new PropertyMap<pl.patrykwoj.entity.Message, pl.patrykwoj.dto.Message>() {
			protected void configure() {
				((pl.patrykwoj.dto.Message) map()).setText(((pl.patrykwoj.entity.Message) this.source).getText());
				map(((pl.patrykwoj.entity.Message) this.source).getType().getName(),
						((pl.patrykwoj.dto.Message) this.destination).getType());
			}
		};
	}

	@Bean
	public PropertyMap<pl.patrykwoj.dto.Message, pl.patrykwoj.entity.Message> messageDtoToEntity() {
		return new PropertyMap<pl.patrykwoj.dto.Message, pl.patrykwoj.entity.Message>() {
			protected void configure() {
				((pl.patrykwoj.entity.Message) map()).setText(((pl.patrykwoj.dto.Message) this.source).getText());
				map(((pl.patrykwoj.dto.Message) this.source).getType(),
						((pl.patrykwoj.entity.Message) this.destination).getType().getName());
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(messageEntityToDto());
		modelMapper.addMappings(messageDtoToEntity());
		return modelMapper;
	}
}
