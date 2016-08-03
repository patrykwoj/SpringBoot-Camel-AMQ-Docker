package pl.pkwk.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
	@Bean
	public PropertyMap<pl.pkwk.entity.Message, pl.pkwk.dto.Message> messageEntityToDto() {
		return new PropertyMap<pl.pkwk.entity.Message, pl.pkwk.dto.Message>() {
			protected void configure() {
				((pl.pkwk.dto.Message) map()).setText(((pl.pkwk.entity.Message) this.source).getText());
				map(((pl.pkwk.entity.Message) this.source).getType().getName(),
						((pl.pkwk.dto.Message) this.destination).getType());
			}
		};
	}

	@Bean
	public PropertyMap<pl.pkwk.dto.Message, pl.pkwk.entity.Message> messageDtoToEntity() {
		return new PropertyMap<pl.pkwk.dto.Message, pl.pkwk.entity.Message>() {
			protected void configure() {
				((pl.pkwk.entity.Message) map()).setText(((pl.pkwk.dto.Message) this.source).getText());
				map(((pl.pkwk.dto.Message) this.source).getType(),
						((pl.pkwk.entity.Message) this.destination).getType().getName());
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
